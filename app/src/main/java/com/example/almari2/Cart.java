package com.example.almari2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    public ArrayList<Item> itemlist;
    public String Token;
    ListView listView;
    Button confirmOrder;
    Button Logout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        Toast.makeText(Cart.this, "Hello  "+user.getEmail(), Toast.LENGTH_SHORT).show();
        itemlist= (ArrayList<Item>) getIntent().getSerializableExtra("items");

        confirmOrder=findViewById(R.id.confirm);
        Logout=(Button)findViewById(R.id.logoutcartwishlist);
        listView = (ListView) findViewById(R.id.cartList);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        CartAdapter adapter = new CartAdapter(Cart.this,itemlist);
        listView.setAdapter(adapter);
    }
}
