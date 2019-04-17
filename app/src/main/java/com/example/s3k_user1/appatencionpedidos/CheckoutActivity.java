package com.example.s3k_user1.appatencionpedidos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.s3k_user1.appatencionpedidos.adapter.CheckRecyclerViewAdapter;
import com.example.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.example.s3k_user1.appatencionpedidos.helpers.SimpleDividerItemDecoration;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;
import com.example.s3k_user1.appatencionpedidos.ui.FragmentoCategorias;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private static final String TAG = CheckoutActivity.class.getSimpleName();

    private RecyclerView checkRecyclerView;

    private TextView subTotal;

    private double mSubTotal = 0;

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        agregarToolbar();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Pedidos");

        subTotal = (TextView )findViewById(R.id.sub_total);

        checkRecyclerView = (RecyclerView)findViewById(R.id.checkout_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
        checkRecyclerView.setLayoutManager(linearLayoutManager);
        checkRecyclerView.setHasFixedSize(true);
        checkRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(CheckoutActivity.this));

        // get content of cart
        MySharedPreference mShared = new MySharedPreference(CheckoutActivity.this);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Comida[] addCartProducts = gson.fromJson(mShared.retrieveProductFromCart(), Comida[].class);
        List<Comida> productList = convertObjectArrayToListObject(addCartProducts);

        CheckRecyclerViewAdapter mAdapter = new CheckRecyclerViewAdapter(CheckoutActivity.this, productList);
        checkRecyclerView.setAdapter(mAdapter);

        mSubTotal = getTotalPrice(productList);
        subTotal.setText("Subtotal : " +  "$ " +String.valueOf(mSubTotal));


        Button shoppingButton = (Button)findViewById(R.id.shopping);
        assert shoppingButton != null;
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shoppingIntent = new Intent(CheckoutActivity.this, FragmentoCategorias.class);
                startActivity(shoppingIntent);
            }
        });

        Button checkButton = (Button)findViewById(R.id.checkout);
        assert checkButton != null;
//        checkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent paymentIntent = new Intent(CheckoutActivity.this, PayPalCheckoutActivity.class);
//                paymentIntent.putExtra("TOTAL_PRICE", mSubTotal);
//                startActivity(paymentIntent);
//            }
//        });
    }

    private List<Comida> convertObjectArrayToListObject(Comida[] allProducts){
        List<Comida> mProduct = new ArrayList<Comida>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    private int returnQuantityByProductName(String productName, List<Comida> mProducts){
        int quantityCount = 0;
        for(int i = 0; i < mProducts.size(); i++){
            Comida pObject = mProducts.get(i);
            if(pObject.getNombre().trim().equals(productName.trim())){
                quantityCount++;
            }
        }
        return quantityCount;
    }

    private double getTotalPrice(List<Comida> mProducts){
        double totalCost = 0;
        for(int i = 0; i < mProducts.size(); i++){
            Comida pObject = mProducts.get(i);
            totalCost = totalCost + pObject.getPrecio();
        }
        return totalCost;
    }
}
