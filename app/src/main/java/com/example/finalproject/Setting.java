package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Setting extends AppCompatActivity {

    private TextView Logout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setupUI();
        firebaseAuth = FirebaseAuth.getInstance();
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void setupUI() {
        Logout = findViewById(R.id.Logout);
    }

    private void logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(Setting.this, MainActivity.class));
    }
}
