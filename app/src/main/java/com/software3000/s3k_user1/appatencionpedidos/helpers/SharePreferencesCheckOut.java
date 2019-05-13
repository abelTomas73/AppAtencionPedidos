package com.software3000.s3k_user1.appatencionpedidos.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.software3000.s3k_user1.appatencionpedidos.modelo.Comida;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SharePreferencesCheckOut {
    private SharedPreferences prefs;
    private Context context;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();
    public SharePreferencesCheckOut(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }
    public void addProductToTheCart(String product){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(Constants.PRODUCT_ID, product);
        edits.apply();
    }

    public void seleccionarMaquina(Comida comidaproducto){
        SharedPreferences.Editor edits = prefs.edit();
        String newMaquinaproducto = gson.toJson(comidaproducto);
        edits.putString(Constants.MAQUINA_ID, newMaquinaproducto);
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
