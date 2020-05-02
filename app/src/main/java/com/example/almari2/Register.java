package com.example.almari2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText emailadd;
    private EditText password;
    private EditText Fullname;
    Button Register;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth=FirebaseAuth.getInstance();

        Register=(Button) findViewById(R.id.signup);
        Fullname=(EditText)findViewById(R.id.Fullname);
        emailadd=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);

        progressDialog=new ProgressDialog(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });
    }
    public void RegisterUser()
    {
        String email = emailadd.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String name = Fullname.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(Register.this, "Please enter email :(", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(Register.this, "Please enter a valid email :(", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(Register.this, "Please enter passsword :(", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(Register.this, "Please enter Fullname :(", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering you");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "Registered Succesfully", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Register.this, "Unable to register", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
