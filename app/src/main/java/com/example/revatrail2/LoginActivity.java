package com.example.revatrail2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText emailid, pass;
    Button login;
    TextView chagetoSignup;
    FirebaseAuth auth;
    android.app.ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading");
        progressDialog.setCancelable(false);


        auth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.loginemailid);
        pass = findViewById(R.id.loginpassword);
        login = findViewById(R.id.login_button);
        chagetoSignup = findViewById(R.id.redirecttosignin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginemail = emailid.getText().toString();
                String loginpassword = pass.
                        getText().toString();

                if (loginemail.isEmpty() || loginpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "enter full details", Toast.LENGTH_SHORT).show();
                } else {
                    if (isValid(loginemail)) {
                        auth.signInWithEmailAndPassword(loginemail, loginpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressDialog.show();
                                    try {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Your Reva mail is not correct", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        chagetoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isValid(String loginemail) {
        String pattern = "[0-9]{7}@reva.edu.in";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(loginemail);

        return m.matches();
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            progressDialog.show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}