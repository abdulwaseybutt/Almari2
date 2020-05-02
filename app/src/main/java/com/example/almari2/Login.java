package com.example.almari2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView Register;
    Button Login;
    private EditText emailadd;
    private EditText password;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!= null){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        Register=(TextView) findViewById(R.id.signup);
        Login=(Button) findViewById(R.id.signin);
        emailadd=(EditText)findViewById(R.id.emailtext);
        password=(EditText)findViewById(R.id.passtext);
        progressDialog=new ProgressDialog(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }
    private void Register()
    {
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }
    private void validate()
    {
        String email = emailadd.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(Login.this, "Please enter email :(", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(Login.this, "Please enter passsword :(", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        finish();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
            }
        });

    }
}
