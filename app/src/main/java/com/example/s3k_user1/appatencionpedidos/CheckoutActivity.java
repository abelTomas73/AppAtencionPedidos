package com.example.s3k_user1.appatencionpedidos;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.s3k_user1.appatencionpedidos.adapter.CheckRecyclerViewAdapter;
import com.example.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.example.s3k_user1.appatencionpedidos.helpers.SimpleDividerItemDecoration;
import com.example.s3k_user1.appatencionpedidos.model.CortesiaProductos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private static final String TAG = CheckoutActivity.class.getSimpleName();

    private RecyclerView checkRecyclerView;

    private static TextView subTotal;

    private double mSubTotal = 0;
    private static String mSubTotalStatico = "";
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private LinearLayout checkout_list_layout;
    private ImageView imagen_carrito_vacio;
    private TextView texto_carrito_vacio;
    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
    public static void  actualizarSubtotal(List<CortesiaProductos> productList){
        double mSubTotal = (double) getTotalPrice(productList);
        subTotal.setText("Subtotal : " +  "$ " +df2.format(mSubTotal ));
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


        checkout_list_layout = findViewById(R.id.checkout_list_layout);
        imagen_carrito_vacio = findViewById(R.id.imagen_carrito_vacio);
        texto_carrito_vacio = findViewById(R.id.texto_carrito_vacio);
        // get content of cart
        MySharedPreference mShared = new MySharedPreference(CheckoutActivity.this);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        CortesiaProductos[] addCartProducts = gson.fromJson(mShared.retrieveProductFromCart(), CortesiaProductos[].class);
        if (addCartProducts==null || addCartProducts.length==0){
            checkout_list_layout.setGravity(Gravity.CENTER);

            imagen_carrito_vacio.setVisibility(View.VISIBLE);
            texto_carrito_vacio.setVisibility(View.VISIBLE);
            checkRecyclerView.setVisibility(View.GONE);

            return;
        }
        //if (addCartProducts==null) return;


        List<CortesiaProductos> productList = convertObjectArrayToListObject(addCartProducts);



        CheckRecyclerViewAdapter mAdapter = new CheckRecyclerViewAdapter(CheckoutActivity.this, productList);
        checkRecyclerView.setAdapter(mAdapter);

        //mAdapter.notifyDataSetChanged();
        //mAdapter.notifyItemRemoved(3);
        mSubTotal = getTotalPrice(productList);


        //subTotal.setText("Subtotal : " +  "$ " + df2.format(mSubTotal ));


//        Button shoppingButton = (Button)findViewById(R.id.shopping);
//        assert shoppingButton != null;
//        shoppingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shoppingIntent = new Intent(CheckoutActivity.this, FragmentoCategorias.class);
//                startActivity(shoppingIntent);
//            }
//        });

        Button checkButton = (Button)findViewById(R.id.checkout);
        assert checkButton != null;
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MySharedPreference sharedPreference = new MySharedPreference(getBaseContext());

            }
        });
    }

    private List<CortesiaProductos> convertObjectArrayToListObject(CortesiaProductos[] allProducts){
        List<CortesiaProductos> mProduct = new ArrayList<CortesiaProductos>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    private int returnQuantityByProductName(String productName, List<CortesiaProductos> mProducts){
        int quantityCount = 0;
        for(int i = 0; i < mProducts.size(); i++){
            CortesiaProductos pObject = mProducts.get(i);
            if(pObject.getNombre().trim().equals(productName.trim())){
                quantityCount++;
            }
        }
        return quantityCount;
    }

    private static double getTotalPrice(List<CortesiaProductos> mProducts){
        double totalCost = 0;
        for(int i = 0; i < mProducts.size(); i++){
            CortesiaProductos pObject = mProducts.get(i);
            totalCost = totalCost;
        }
        return totalCost;
    }
}
