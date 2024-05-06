package com.abhijeet.travel_saathi.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.activities.Home_page;
import com.abhijeet.travel_saathi.activities.Signup_successfully;
import com.abhijeet.travel_saathi.models.UserModel;
import com.abhijeet.travel_saathi.utilities.GradientTextView;
import com.abhijeet.travel_saathi.utilities.MailHelper;
import com.abhijeet.travel_saathi.utilities.OtpFlowManager;
import com.abhijeet.travel_saathi.utils.FirebaseUtil;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class NewLoginActivity extends AppCompatActivity {

    GradientTextView headLogintext;
    FrameLayout otpDetails;

    TextInputEditText emailField;

    TextInputLayout layout;

    MaterialButton sendOtp;
    ImageView nextButton;

    String otp;
    UserModel currentUserModel;
    String enteredOTP;
    MaterialCardView googleButton;
    TextView resendOtp;

    private FirebaseAuth mAuth;

    TextInputEditText firstDigit, secondDigit, thirdDigit, fourthDigit;
    OtpFlowManager flowManager;
    UserModel userModel;


    boolean flag = false; // false means login type is email
    // reset the head

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        flowManager = new OtpFlowManager(this);

        mAuth = FirebaseAuth.getInstance();

        initializeID();
        initializeViews();

    }


    public void initializeViews() {
        headLogintext.setGradientColors(0xFF001AFF, 0xFFFB78E6);

        BottomSheetDialog loginDialog = new BottomSheetDialog(this);
        loginDialog.setContentView(R.layout.login_bottomsheet);

        FrameLayout bottomsheet = loginDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet);

        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) bottomsheet.getLayoutParams();
        layoutParams.leftMargin = 16;
        layoutParams.rightMargin = 16;
        bottomsheet.setLayoutParams(layoutParams);

        loginDialog.setCancelable(false);
        loginDialog.show();
        otpDetails = loginDialog.findViewById(R.id.otpDetails);
        assert otpDetails != null;
        otpDetails.setVisibility(View.GONE);

        googleButton = loginDialog.findViewById(R.id.materialCardView3);

        emailField = loginDialog.findViewById(R.id.textInputEditText);
        sendOtp = loginDialog.findViewById(R.id.sendOTP);
        nextButton = loginDialog.findViewById(R.id.nextButton);

        firstDigit = loginDialog.findViewById(R.id.digitOne);
        secondDigit = loginDialog.findViewById(R.id.digitTwo);
        thirdDigit = loginDialog.findViewById(R.id.digitThree);
        fourthDigit = loginDialog.findViewById(R.id.digitFour);

        layout = loginDialog.findViewById(R.id.textInputLayout);

        resendOtp = loginDialog.findViewById(R.id.resendOtp);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);


        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag) {
                    if (!emailField.getText().toString().isEmpty()) {
                        hideKeyboard(view);
                        Random random = new Random();
                        int number = 1000 + random.nextInt(9000);
                        otp = String.valueOf(number);
                        Log.d("Otp sent", "onClick: " + otp);
                        MailHelper.sendEmail(emailField.getText().toString(), otp);
                        Toast.makeText(NewLoginActivity.this, "Otp Sent", Toast.LENGTH_SHORT).show();
                        otpDetails.setVisibility(View.VISIBLE);
                        showKeyboard(firstDigit);
                        flowManager.initializeOtpBoxFlow(firstDigit,secondDigit,thirdDigit,fourthDigit);

                    } else {
                        Toast.makeText(NewLoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (!emailField.getText().toString().isEmpty()) {
                        hideKeyboard(view);
                        Toast.makeText(NewLoginActivity.this, "Otp Sent", Toast.LENGTH_SHORT).show();
                        otpDetails.setVisibility(View.VISIBLE);
                        sendSMS(emailField.getText().toString());
                    } else {
                        Toast.makeText(NewLoginActivity.this, "Enter Phone", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredOTP = firstDigit.getText().toString() + secondDigit.getText() + thirdDigit.getText() + fourthDigit.getText();
                Log.d("Otp entered", "onClick: " + enteredOTP);
                if (enteredOTP.equals(otp)) {
                    loginUser(emailField.getText().toString(), emailField.getText().toString());
                } else {
                    Toast.makeText(NewLoginActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emailField.getText().toString().isEmpty()){
                    hideKeyboard(view);
                    Random random = new Random();
                    int number = 1000 + random.nextInt(9000);
                    otp = String.valueOf(number);
                    Toast.makeText(NewLoginActivity.this, "Otp Sent", Toast.LENGTH_SHORT).show();
                    otpDetails.setVisibility(View.VISIBLE);
                    MailHelper.sendEmail(emailField.getText().toString(), otp);
                }
                else{
                    Toast.makeText(NewLoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        googleButton.setOnClickListener(view -> {
            // flag = 1 means login via google
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, 1000);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                String email = account.getEmail();
                String pass = account.getId();
                loginUser(email, pass);
            } catch (ApiException e) {
                Log.v("Error", e.getMessage());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void initializeID() {
        headLogintext = findViewById(R.id.headLoginText);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private void sendSMS(String phoneNo) {
        Random random = new Random();
        int number = 1000 + random.nextInt(9000);
        otp = String.valueOf(number);

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+91" + phoneNo, null, otp, null, null);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("LOG MESSAGE", e.getMessage().toString());
            Toast.makeText(this, "Message Not Sent", Toast.LENGTH_SHORT).show();
        }
    }

    // reset

    public void showKeyboard(TextInputEditText editText){
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });
    }
    private void loginUser(String email, String password) {
        try{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success
                                Intent intent = new Intent(NewLoginActivity.this, Home_page.class);

                                SharedPreferences sharedPreferences = getSharedPreferences("OnceLoggedIn", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isLoggedIn", true);
                                editor.putString("Email", email);
                                Log.v("EMAIL", email);
                                editor.apply();
                                getUserData();
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);                            // You can navigate to another activity or perform other actions here
                            } else {
                                Toast.makeText(NewLoginActivity.this, "this is", Toast.LENGTH_SHORT).show();
                                signupUser(email, password);
                                // If sign in fails, display a message to the user.
                            }
                        }
                    });
        }catch (Exception e){
            Toast.makeText(NewLoginActivity.this, "this is", Toast.LENGTH_SHORT).show();

            Log.v("Sign up error", e.getCause().getMessage());

        }

    }
    private void loginUser_direct(String email, String password) {
        try{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success
                                Intent intent = new Intent(NewLoginActivity.this, Signup_successfully.class);

                                SharedPreferences sharedPreferences = getSharedPreferences("OnceLoggedIn", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("isLoggedIn", true);
                                editor.putString("Email", email);
                                Log.v("EMAIL", email);
                                editor.apply();
                                getUserData();
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);                            // You can navigate to another activity or perform other actions here
                            } else {
                                Toast.makeText(NewLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }catch (Exception e){
            Toast.makeText(NewLoginActivity.this, "this is", Toast.LENGTH_SHORT).show();

            Log.v("Sign up error", e.getCause().getMessage());

        }

    }
    private void signupUser(String email, String password) {
        try{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        loginUser_direct(email, password);
                    }else{
                        Toast.makeText(NewLoginActivity.this, task.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }catch (Exception e){
            Log.v("Sign up error", e.getMessage().toString());
        }
    }

    void getUserData(){
        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(task -> {

            SharedPreferences sh = getSharedPreferences("user_data", MODE_PRIVATE);
            SharedPreferences.Editor ed = sh.edit();
            currentUserModel = task.getResult().toObject(UserModel.class);

            ed.putString("USERNAME", currentUserModel.getUsername());
            ed.commit();
        });
    }
}