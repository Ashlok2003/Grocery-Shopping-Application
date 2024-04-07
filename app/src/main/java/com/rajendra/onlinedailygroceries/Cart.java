package com.rajendra.onlinedailygroceries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.rajendra.onlinedailygroceries.adapter.CartAdapter;
import com.rajendra.onlinedailygroceries.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    List<CartItem> cartItemList;

    FirebaseFirestore db;

    Button buyNow;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        buyNow = findViewById(R.id.buy_now);
        back = findViewById(R.id.back_button);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recycler_view_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartItemList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartItemList);
        recyclerView.setAdapter(cartAdapter);

        fetchCartItems();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove all cart items from Firebase Firestore
                db.collection("cart")
                        .get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                documentSnapshot.getReference().delete();
                            }
                            // Clear the local cartItemList
                            cartItemList.clear();
                            cartAdapter.notifyDataSetChanged();

                            // Redirect the user to another window
                            Intent intent = new Intent(getApplicationContext(), thank_you.class);
                            startActivity(intent);
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            // Handle any errors
                            Toast.makeText(Cart.this, "Failed to clear cart: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

    }

    private void fetchCartItems() {
        db.collection("cart")
                .orderBy("productName", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String productName = document.getString("productName");
                            String productDesc = document.getString("productDesc");
                            String productPrice = document.getString("productPrice");
                            String productQty = document.getString("productQty");
                            String productUnit = document.getString("productUnit");


                            CartItem cartItem = new CartItem(productName, productDesc, productPrice, productQty, productUnit, getImageId(productName));
                            cartItemList.add(cartItem);
                        }
                        cartAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(Cart.this, "Your Cart is Empty", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public int getImageId(String name) {
        switch(name) {
            case "Watermelon" : {
                return R.drawable.b4;
            }
            case "Papaya" : {
                return R.drawable.b3;
            }
            case "Strawberry" : {
                return R.drawable.b1;
            }
            case "Kiwi" : {
                return R.drawable.b2;
            }

            default: {
                return R.drawable.buy_now_bg;
            }
        }
    }

}
