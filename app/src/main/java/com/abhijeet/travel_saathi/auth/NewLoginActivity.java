package com.abhijeet.travel_saathi.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.activities.Home_page;
import com.abhijeet.travel_saathi.utilities.GradientTextView;
import com.abhijeet.travel_saathi.utilities.MailHelper;
import com.abhijeet.travel_saathi.utilities.OtpFlowManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NewLoginActivity extends AppCompatActivity {

    GradientTextView headLogintext;
    FrameLayout otpDetails;

    TextInputEditText emailField;

    TextInputLayout layout;

    MaterialButton sendOtp;
    ImageView nextButton;

    String otp;
    String enteredOTP;
    MaterialCardView googleButton;
    TextView resendOtp;
    TextView logInPhone;

    TextInputEditText firstDigit, secondDigit, thirdDigit, fourthDigit;
    OtpFlowManager flowManager;


    boolean flag = false; // false means login type is email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        flowManager = new OtpFlowManager(this);

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
        logInPhone = loginDialog.findViewById(R.id.loginPhonebutton);

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
                    Intent intent = new Intent(NewLoginActivity.this, Home_page.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(NewLoginActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logInPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailField.setInputType(InputType.TYPE_CLASS_NUMBER);
                emailField.setSingleLine();
                layout.setHint("Enter Phone");
                emailField.setHint("Enter Phone Number");
                flag = true;
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
}