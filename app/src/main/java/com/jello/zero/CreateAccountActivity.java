package com.jello.zero;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private static final String TAG = "CreateAccount";
    private FirebaseDatabase database;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
    }

    public void createUserWithEmailAndPassword(View view){
        auth = FirebaseAuth.getInstance();
        String email = ((EditText)findViewById(R.id.email_signup)).getText().toString();
        String password = ((EditText)findViewById(R.id.password_signup)).getText().toString();
        final String zipcode = ((EditText)findViewById(R.id.zip_code_signup)).getText().toString();
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                if(task.isSuccessful()){
                    addUser(auth.getCurrentUser().getUid(), auth.getCurrentUser().getEmail(), zipcode);
                    accountCreated();
                }else{
                    Toast.makeText(CreateAccountActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addUser(String uid, String email, String zipcode){
        User newUser = new User(email, zipcode);
        usersRef.child(uid).setValue(newUser);
    }

    public void accountCreated(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void switchToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
