package com.abhijeet.travel_saathi.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.activities.Signup_successfully;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginSignup extends AppCompatActivity {

    Button home_page;
    private boolean isLoginTransitioned = false;
    private boolean isSignupTransitioned1 = false;
    private EditText loginMail, loginPass, signupMail, signupPass, userName, phoneNo;
    private FirebaseAuth mAuth;

    private Button logIn, signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        MotionLayout motionLayout1 = findViewById(R.id.loginSignupCardScene);
        MotionLayout motionLayout2 = findViewById(R.id.loginCardComponentsScene);
        MotionLayout motionLayout3 = findViewById(R.id.signupCardComponentsScene);

        mAuth = FirebaseAuth.getInstance();

        loginMail = findViewById(R.id.editTextText);
        loginPass = findViewById(R.id.editTextText2);

        signupMail = findViewById(R.id.editTextText4);
        signupPass = findViewById(R.id.editTextText5);

        logIn = findViewById(R.id.button);
        signUp = findViewById(R.id.button1);

    //for home_page
        home_page = findViewById(R.id.button);

        CardView loginCard = findViewById(R.id.loginCard);
        CardView SignupCard = findViewById(R.id.singupCard);
        loginCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLoginTransitioned){
                    motionLayout1.transitionToState(R.id.login);
                    motionLayout2.transitionToState(R.id.login_components);
                }
                else{
                    motionLayout1.transitionToState(R.id.start);
                    motionLayout2.transitionToStart();
                    motionLayout3.transitionToState(R.id.start);
                }
                isLoginTransitioned = !isLoginTransitioned;
            }
        });
        SignupCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                isLoginTransitioned = !isLoginTransitioned;
                if (!isSignupTransitioned1){
                    motionLayout1.transitionToState(R.id.signup);
                    motionLayout3.transitionToState(R.id.signup_components);
                }
                else{
                    motionLayout1.transitionToState(R.id.start);
                    motionLayout2.transitionToStart();
                    motionLayout3.transitionToState(R.id.start);
                }
                isSignupTransitioned1 = !isSignupTransitioned1;
            }
        });

        home_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginSignup.this, Signup_successfully.class);
                startActivity(intent);
                finish();
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        vibrator.vibrate(50);
                    }
                }
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginMail.getText().toString();
                String password = loginPass.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()){
                    loginUser(email, password);
                }else{
                    Toast.makeText(LoginSignup.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signupMail.getText().toString();
                String password = signupPass.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()){
                    signupUser(email,password);
                }else{
                    Toast.makeText(LoginSignup.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Toast.makeText(LoginSignup.this, "Login successful", Toast.LENGTH_SHORT).show();
                            // You can navigate to another activity or perform other actions here
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginSignup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signupUser(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success
                            Toast.makeText(LoginSignup.this, "Signup successful", Toast.LENGTH_SHORT).show();
                            // You can navigate to another activity or perform other actions here
                        } else {
                            // If sign up fails, display a message to the user.
                            Toast.makeText(LoginSignup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}