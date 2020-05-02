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

public class Wishlist extends AppCompatActivity {
    public ArrayList<Item> itemlist;
    public String Token;
    ListView listView;
    Button confirmOrder;
    Button Logout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        itemlist = (ArrayList<Item>) getIntent().getSerializableExtra("items");
        Toast.makeText(Wishlist.this, "Hello  "+user.getEmail(), Toast.LENGTH_SHORT).show();

        Logout = (Button) findViewById(R.id.logoutcartwishlist2);
        listView = (ListView) findViewById(R.id.wishlistList);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
        CartAdapter adapter = new CartAdapter(Wishlist.this,itemlist);
        listView.setAdapter(adapter);
    }
}