package com.software3000.s3k_user1.appatencionpedidos.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.software3000.s3k_user1.appatencionpedidos.CheckoutActivity;
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaProductos;

import com.software3000.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.software3000.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewHolder> {

    private Context context;

    private List<CortesiaProductos> mProductObject;
    private MySharedPreference sharedPreference;

    private int cartProductNumber = 0;

    public CheckRecyclerViewAdapter(Context context, List<CortesiaProductos> mProductObject) {
        this.context = context;
        this.mProductObject = mProductObject;
    }

    @Override
    public CheckRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_layout, parent, false);
        final CheckRecyclerViewHolder productHolder = new CheckRecyclerViewHolder(layoutView);
        productHolder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=productHolder.getAdapterPosition();
                //Toast.makeText(context,"Item at position "+position+" deleted",Toast.LENGTH_SHORT).show();
                //mProductObject.remove(position);
                notifyDataSetChanged();
            }
        });
        return productHolder;
    }
    // inici mismo meth

    private List<CortesiaProductos> convertObjectArrayToListObject(CortesiaProductos[] allProducts){
        List<CortesiaProductos> mProduct = new ArrayList<CortesiaProductos>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    // fin mis meth
    @Override
    public void onBindViewHolder(final CheckRecyclerViewHolder holder, final int position) {
        sharedPreference = new MySharedPreference(ActividadPrincipal.contextoAcPrincipal);
        GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        String productsInCart = sharedPreference.retrieveProductFromCart();
        CortesiaProductos[] storedProducts = gson.fromJson(productsInCart, CortesiaProductos[].class);

        final List<CortesiaProductos> allNewProduct = convertObjectArrayToListObject(storedProducts);




        //get product quantity
        holder.quantity.setText("1");
        byte[] bytearray = Base64.decode(mProductObject.get(position).getArchivo64String(), 0);
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytearray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);


        holder.imagen_product.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 192,
                192, false));
        holder.productName.setText(mProductObject.get(position).getNombre());
        holder.productPrice.setText("");

        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sharedPreference.deleteAllProductsFromTheCart();

                //se remueve el producto elegido (indice ) y reutilizar el metodo
                // .addProductToTheCart
                allNewProduct.remove(position);


                String addAndStoreNewProduct = gson.toJson(allNewProduct);

                //sharedPreference.deleteProductfromTheCart(addAndStoreNewProduct);

                Toast.makeText(context, "Item removido de la carta", Toast.LENGTH_LONG).show();

                mProductObject.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());

                addAndStoreNewProduct = gson.toJson(mProductObject);
                sharedPreference.addProductToTheCart(addAndStoreNewProduct);

                cartProductNumber = mProductObject.size();
                //notifyItemChanged(holder.getAdapterPosition());


                CheckoutActivity.actualizarSubtotal(mProductObject);

                sharedPreference.addProductCount(cartProductNumber);
                ActivityCompat.invalidateOptionsMenu(FragmentoInicio.activitydelFragmento);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mProductObject.size();
    }
}

