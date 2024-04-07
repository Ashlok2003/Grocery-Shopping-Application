package com.rajendra.onlinedailygroceries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProductDetails extends AppCompatActivity {

    ImageView img, back, addToCart;
    Button buyNow;
    TextView proName, proPrice, proDesc, proQty, proUnit;

    String name, price, desc, qty, unit;
    int image;

    ImageView cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent i = getIntent();

         name = i.getStringExtra("name");
         image = i.getIntExtra("image", R.drawable.b1);
         price = i.getStringExtra("price");
         desc = i.getStringExtra("desc");
         qty = i.getStringExtra("qty");
         unit = i.getStringExtra("unit");

         proName = findViewById(R.id.productName);
         proDesc = findViewById(R.id.prodDesc);
         proPrice = findViewById(R.id.prodPrice);
         img = findViewById(R.id.big_image);
         back = findViewById(R.id.back_button);
         proQty = findViewById(R.id.qty);
         proUnit = findViewById(R.id.unit);

         proName.setText(name);
         proPrice.setText(price);
         proDesc.setText(desc);
         proQty.setText(qty);
         proUnit.setText(unit);

         
        img.setImageResource(image);

        cart = findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        addToCart = findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String productName = proName.getText().toString();
                String productDesc = proDesc.getText().toString();
                String productPrice = proPrice.getText().toString();
                String productQty = proQty.getText().toString();
                String productUnit = proUnit.getText().toString();


                Map<String, Object> product = new HashMap<>();
                product.put("productName", productName);
                product.put("productDesc", productDesc);
                product.put("productPrice", productPrice);
                product.put("productQty", productQty);
                product.put("productUnit", productUnit);


                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("cart")
                        .add(product)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ProductDetails.this, "Product added to cart!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProductDetails.this, "Failed to add product to cart!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        buyNow = findViewById(R.id.buy_now);
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetails.this, "You Order will be Processed Soon", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), thank_you.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
