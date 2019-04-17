package com.example.s3k_user1.appatencionpedidos.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    private SharedPreferences prefs;
    private Context context;
    public MySharedPreference(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }
    public void addProductToTheCart(String product){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(Constants.PRODUCT_ID, product);
        edits.apply();
    }
    public String retrieveProductFromCart(){
        return prefs.getString(Constants.PRODUCT_ID, "");
    }
    public void addProductCount(int productCount){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putInt(Constants.PRODUCT_COUNT, productCount);
        edits.apply();
    }
    public int retrieveProductCount(){
        return prefs.getInt(Constants.PRODUCT_COUNT, 0);
    }


    public void deleteAllProductsFromTheCart( ){
        prefs.edit().clear().commit();
    }

    public void deleteProductfromTheCart(String product){


        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(Constants.PRODUCT_ID);
        editor.commit();
    }
}
