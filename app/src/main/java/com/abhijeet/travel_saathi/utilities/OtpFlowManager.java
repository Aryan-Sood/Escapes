package com.abhijeet.travel_saathi.utilities;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.textfield.TextInputEditText;

public class OtpFlowManager {


    static Context context;

    public OtpFlowManager(Context context) {
        this.context = context;
    }

    public static void initializeOtpBoxFlow(TextInputEditText firstDigit, TextInputEditText secondDigit, TextInputEditText thirdDigit, TextInputEditText fourthDigit){
        firstDigit.requestFocus();

        firstDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()==1){
                    secondDigit.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        secondDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                showKeyboard(firstDigit);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()==1){
                    thirdDigit.requestFocus();
                }
                else if (charSequence.length()==0){
                    firstDigit.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        thirdDigit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                showKeyboard(secondDigit);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()==1){
                    fourthDigit.requestFocus();
                }
                else if (charSequence.length()==0){
                    secondDigit.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        fourthDigit.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                showKeyboard(thirdDigit);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()==0){
                    thirdDigit.requestFocus();
                }

                else if (charSequence.length()==1){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(fourthDigit.getWindowToken(),0);
//                            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(firstDigit.getWindowToken(), 0);
                        }
                    },500);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        secondDigit.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (secondDigit.length()==0){
//                    if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL || keyEvent.getKeyCode() == KeyEvent.KEYCODE_FORWARD_DEL){
//                        firstDigit.requestFocus();
//                    }
//                }
//                return true;
//            }
//        });
//
//
//        thirdDigit.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (thirdDigit.length()==0){
//                    if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL || keyEvent.getKeyCode() == KeyEvent.KEYCODE_FORWARD_DEL){
//                        secondDigit.requestFocus();
//                    }
//                }
//                return true;
//            }
//        });
//
//        fourthDigit.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if (fourthDigit.length()==0){
//                    if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DEL || keyEvent.getKeyCode() == KeyEvent.KEYCODE_FORWARD_DEL){
//                        thirdDigit.requestFocus();
//                    }
//                }
//                return true;
//            }
//        });
    }

//    public static void showKeyboard(TextInputEditText editText){
//        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (b){
////                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
////                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
//                }
//            }
//        });
//    }


}
