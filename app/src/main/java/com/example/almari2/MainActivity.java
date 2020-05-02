package com.example.almari2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button Logout;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    GridView listView;
    ArrayList<Item> itemList;
    public String Token;
    public ArrayList<Item> cart=new ArrayList<>();
    public ArrayList<Item> wishlist=new ArrayList<>();
    private PopupWindow mPopupWindow;
    private LinearLayout mRelativeLayout;

    Button gocart;
    Button gowishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        Token=i.getStringExtra("token");
        listView = (GridView) findViewById(R.id.listView);
        itemList = new ArrayList<>();
        mRelativeLayout = (LinearLayout) findViewById(R.id.product);
        Logout=(Button) findViewById(R.id.logout);
        gocart=(Button)findViewById(R.id.Cart);
        gowishlist=(Button)findViewById(R.id.wishlist);

        Item item1=new Item(1,"CREW NECK TEE SHIRT 1","https://cdn.shopify.com/s/files/1/0671/0621/products/Blue-Marl_b702bf9d-1656-4710-8b0f-c46e8c75ddc3_2000x.jpg?v=1588157587",999);
        Item item2=new Item(1,"CREW NECK TEE SHIRT 2","https://cdn.shopify.com/s/files/1/0671/0621/products/White_17c4da2f-41bb-4587-afef-f678d4019dde.jpg?v=1588177563",900);
        Item item3=new Item(1,"CREW NECK TEE SHIRT 3","https://cdn.shopify.com/s/files/1/0671/0621/products/Charcoal_56a272de-566d-4ba8-b18c-7150a3c8bc50.jpg?v=1588177613",800);
        Item item4=new Item(1,"CREW NECK TEE SHIRT 4","https://cdn.shopify.com/s/files/1/0671/0621/products/Yellow_520edfaf-ba9f-4c8d-b1b5-39291d7301fd.jpg?v=1588177549",1100);
        Item item5=new Item(1,"CREW NECK TEE SHIRT 5","https://cdn.shopify.com/s/files/1/0671/0621/products/Brick-re-Marl.jpg?v=1588157898",1500);
        Item item6=new Item(1,"CREW NECK TEE SHIRT 6","https://cdn.shopify.com/s/files/1/0671/0621/products/Green_d0e9db62-0bd7-44a0-a710-b83d3786ecce.jpg?v=1588177589",300);
        Item item7=new Item(1,"CREW NECK TEE SHIRT 7","https://cdn.shopify.com/s/files/1/0671/0621/products/Mint-Green.jpg?v=1588177519",600);
        Item item8=new Item(1,"CREW NECK TEE SHIRT 7","https://cdn.shopify.com/s/files/1/0671/0621/products/Heathergrey_32d597ea-df01-4a8c-98df-7cf895e0f560.jpg?v=1588177605",500);
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        itemList.add(item6);
        itemList.add(item7);
        itemList.add(item8);

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        Toast.makeText(MainActivity.this, "Hello  "+user.getEmail(), Toast.LENGTH_SHORT).show();

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        gocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Cart.class);
                intent.putExtra("items",cart);
                startActivity(intent);
            }
        });
        gowishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Wishlist.class);
                intent.putExtra("items",wishlist);
                startActivity(intent);
            }
        });


        HomeAdapter adapter = new HomeAdapter(MainActivity.this,itemList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                View customView = inflater.inflate(R.layout.custom_layout,null);
                mPopupWindow = new PopupWindow(
                        customView,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT
                );
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }

                // Get a reference for the custom view close button
                Button addcart = (Button) customView.findViewById(R.id.addCart);
                Button addwishlist = (Button) customView.findViewById(R.id.addwishlist);
                Button Close = (Button) customView.findViewById(R.id.close);

                addcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cart.add(itemList.get(position));
                        mPopupWindow.dismiss();
                    }
                });
                addwishlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wishlist.add(itemList.get(position));
                        mPopupWindow.dismiss();
                    }
                });
                Close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,180);
            }
        });
    }
}
