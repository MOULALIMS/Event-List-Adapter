package com.example.revatrail2;

import static android.os.Build.VERSION_CODES.R;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpActivity extends AppCompatActivity {

    EditText name,email,password,phone,edit_text_otp;
    Button signup,otpverifybutton;
    TextView changetologin;
    FirebaseAuth auth;
    String mverificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        auth = FirebaseAuth.getInstance();
        name = findViewById(R.id.signupusername);
        email = findViewById(R.id.signupemailid);
        phone = findViewById(R.id.signupphonnenumber);
        password =findViewById(R.id.signuppassword);
        signup = findViewById(R.id.signup_button);
        changetologin = findViewById(R.id.redirecttologin);
        edit_text_otp = findViewById(R.id.edit_text_otp);


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(getApplicationContext(),"failed to verify",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                mverificationId = verificationId;
                mResendToken = forceResendingToken;
                showVerificationdialog(); // Call the showVerificationdialog() method here
            }

        };

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String signemail = email.getText().toString();
                String signpassword = password.getText().toString();
                String phonenumber = phone.getText().toString();
                if(signemail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"email should not be empty",Toast.LENGTH_SHORT).show();
                }
                if(signpassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"password should not be empty",Toast.LENGTH_SHORT).show();
                }
                if(!valid(signemail)){
                    Toast.makeText(getApplicationContext(),"Enter Reva maild ID",Toast.LENGTH_SHORT).show();

                }
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        String.valueOf(phone),
                        60,
                        TimeUnit.SECONDS,
                        SignUpActivity.this,
                        mCallbacks
                );
            }
        });

        changetologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showVerificationdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_otp_verify,null);
        builder.setView(dialogView);

        final EditText editText = dialogView.findViewById(R.id.edit_text_otp);
        editText.setHint("enter code");
        builder.setView(editText);

        otpverifybutton = findViewById(R.id.verifyotpbutton);

        otpverifybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editText.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mverificationId,code);
                signInWithPhoneAuthCredential(credential);
            }
        });

        builder.setCancelable(false);
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }


    private boolean valid(String signemail) {
        String pattern = "[0-9]{7}@reva.edu.in";
        Pattern r =Pattern.compile(pattern);
        Matcher m = r.matcher(signemail);

        return m.matches();
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"verification success",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"verification failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}