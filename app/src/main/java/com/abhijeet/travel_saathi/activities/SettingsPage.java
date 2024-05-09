package com.abhijeet.travel_saathi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.auth.NewLoginActivity;
import com.abhijeet.travel_saathi.utils.FirebaseUtil;
import com.google.android.gms.tasks.Task;

public class SettingsPage extends AppCompatActivity {
    TextView logout, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        delete = findViewById(R.id.delete_text);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUtil.delete();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task<Void> task = FirebaseUtil.delete();
                if(task.isSuccessful()){
                    startActivity(new Intent(SettingsPage.this, NewLoginActivity.class));
                }else{
                    startActivity(new Intent(SettingsPage.this, Home_page.class));
                }
            }
        });
    }
}