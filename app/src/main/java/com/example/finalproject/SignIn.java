package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private EditText UserName;
    private EditText EmailAddress;
    private EditText PassWord;
    private Button SignIn;
    private Button BackToLogin;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setupUI();
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (entryValid()) {
                    String ea = EmailAddress.getText().toString();
                    String pw = PassWord.getText().toString();
                    firebaseAuth.createUserWithEmailAndPassword(ea, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignIn.this, "SignIn succeeded", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignIn.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignIn.this, "SignIn failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });
        BackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void setupUI() {
        UserName = findViewById(R.id.UserName);
        EmailAddress = findViewById(R.id.EmailAddress);
        PassWord = findViewById(R.id.PassWord);
        SignIn = findViewById(R.id.SignIn);
        BackToLogin = findViewById(R.id.BackToLogin);
    }

    private boolean entryValid() {
        String un = UserName.getText().toString();
        String ea = EmailAddress.getText().toString();
        String pw = PassWord.getText().toString();
        boolean valid = false;
        if (un.isEmpty() || ea.isEmpty() || pw.isEmpty()) {
            Toast.makeText(this, "Entry is not valid", Toast.LENGTH_SHORT).show();
        } else {
            valid = true;
        }
        return valid;
    }
}
