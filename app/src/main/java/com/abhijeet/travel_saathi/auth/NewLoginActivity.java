package com.abhijeet.travel_saathi.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.activities.Home_page;
import com.abhijeet.travel_saathi.utilities.GradientTextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


import java.io.UnsupportedEncodingException;
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

    MaterialButton sendOtp;
    ImageView nextButton;

    String otp;
    String enteredOTP;
    MaterialButton googleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        initializeID();
        initializeViews();
    }



    public void initializeViews(){
        headLogintext.setGradientColors(0xFF001AFF,0xFFFB78E6);

        BottomSheetDialog loginDialog = new BottomSheetDialog(this);
        loginDialog.setContentView(R.layout.login_bottomsheet);
        loginDialog.setCancelable(false);
        loginDialog.show();

        otpDetails = loginDialog.findViewById(R.id.otpDetails);
        assert otpDetails != null;
        otpDetails.setVisibility(View.GONE);

        googleButton = loginDialog.findViewById(R.id.googleButton);

        emailField = loginDialog.findViewById(R.id.textInputEditText);
        sendOtp = loginDialog.findViewById(R.id.sendOTP);
        nextButton = loginDialog.findViewById(R.id.nextButton);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);




        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emailField.getText().toString().isEmpty()){
                    hideKeyboard(view);
                    Toast.makeText(NewLoginActivity.this, "Otp Sent", Toast.LENGTH_SHORT).show();
                    otpDetails.setVisibility(View.VISIBLE);
                    Email(emailField.getText().toString());
                }
                else{
                    Toast.makeText(NewLoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enteredOTP.equals(otp)){
                    Intent intent = new Intent(NewLoginActivity.this, Home_page.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(NewLoginActivity.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        googleButton.setOnClickListener(view -> {
//            // flag = 1 means login via google
//            SharedPreferences sharedPreferences2 = getSharedPreferences("tokenStorage",MODE_PRIVATE);
//            SharedPreferences.Editor editor2 = sharedPreferences2.edit();
//            String flag="100";
//            editor2.putString("loginType", flag);
//            editor2.putBoolean("hasLoggedIn",true);
//            editor2.commit();
//
//            SharedPreferences sharedPreferences = getSharedPreferences("screenTime",MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//
//            editor.putLong("usage_mon",3600);
//            editor.putLong("usage_tue",5481);
//            editor.putLong("usage_wed",4726);
//            editor.putLong("usage_thu",2495);
//            editor.putLong("usage_fri",1374);
//            editor.putLong("usage_sat",5866);
//            editor.putLong("usage_sun",2764);
//            editor.putBoolean("reset_data",false);
//            editor.putInt("current_day",-1);
//            editor.commit();
//
//            Intent signInIntent = googleSignInClient.getSignInIntent();
//            startActivityForResult(signInIntent, 1000);
//        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 1000) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                String name = account.getGivenName();
//
//                SharedPreferences sharedPreferencesX = getSharedPreferences("tokenStorage",MODE_PRIVATE);
//                SharedPreferences.Editor editorX = sharedPreferencesX.edit();
//                editorX.putString("userName",name).apply();
//                userIsPresent(account.getEmail());
//
//                if(check){
//                    Log.d("user","Logged in");
//                    String flag = "100";
//                    loginFunction(account.getEmail(), "1234abcd");
//                    SharedPreferences sharedPreferences = getSharedPreferences("tokenStorage", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("loginType",flag);
//                    String nameS = account.getGivenName();
//                    editor.putString("flag","100");
//                    editor.commit();
//                    passNext(nameS);
//
//
//                }
//
//                else{
//                    Log.d("user","Logged in");
//                    String flag = "100";
//                    SharedPreferences sharedPreferences = getSharedPreferences("tokenStorage", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("loginType",flag);
//                    editor.putBoolean("hasLoggedIn",true);
//                    editor.commit();
//
//                    String nameS = account.getGivenName();
//                    passNext(nameS);
//                    UserData userData = new UserData();
//
//                    Random random = new Random();
//                    int min = 100000;
//                    int max = 999999;
//                    int randomSixDigitInteger = random.nextInt(max - min + 1) + min;
//
//                    assert name != null;
//                    userData.setName(name.split(" ")[0] + randomSixDigitInteger);
//                    userData.setGuardianName(name);
//                    userData.setEmail(account.getEmail());
//                    userData.setPassword("1234abcd");
//                    userData.setPhone("1234567890");
//                    userData.setCode("91");
//                    signupFunction(userData);
//                }
//            } catch (ApiException e) {
//                Log.v("CHECK", check +"");
//                Toast.makeText(this, "Error404", Toast.LENGTH_SHORT).show();
//            } catch (UnsupportedEncodingException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }


    public void initializeID(){
        headLogintext = findViewById(R.id.headLoginText);
    }

    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager!=null){
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    public void Email(String email){
        Random random = new Random();
        int number = 1000 + random.nextInt(9000);
        otp = String.valueOf(number);
        try {
            String stringSenderEmail = "aryansood12@gmail.com";
            String stringReceiverEmail = email;
            String stringPasswordSenderEmail = "dvixfhdetanukjws";

            String stringHost = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", stringHost);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                }
            });



            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

            mimeMessage.setSubject("Subject: Android App email");
            mimeMessage.setText(otp);
//            mimeMessage.setText(e2.getText().toString());
//            Toast.makeText(getApplicationContext(), "EMAIL SENT SUCCESSFULLY", Toast.LENGTH_LONG).show();


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    private void sendSMS(String phoneNo){
        Random random = new Random();
        int number = 1000 + random.nextInt(9000);
        otp = String.valueOf(number);

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo,null,otp,null,null);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Message Not Sent", Toast.LENGTH_SHORT).show();
        }
    }
}