package com.me.njerucyrus.uberjob;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private final String TAG = "SingUpActivity";
    EditText mEmail, mFullName, mPassword;
    String txtEmail , txtFullName, txtPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignUp = (Button)findViewById(R.id.btnRegister);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser !=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }
    }

    public void registerUser(){
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        mEmail = (EditText) findViewById(R.id.txtEmail);
        txtEmail  = mEmail.getText().toString();

        mFullName = (EditText) findViewById(R.id.txtFullName);
        txtFullName = mFullName.getText().toString();

        mPassword = (EditText)findViewById(R.id.txtPassword);
        txtPassword =  mPassword.getText().toString().trim();
        final User userObject = new User(txtEmail, txtFullName);

        mAuth.createUserWithEmailAndPassword(txtEmail, txtPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            db.collection("users").add(userObject);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Failed to create account.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });

    }
}
