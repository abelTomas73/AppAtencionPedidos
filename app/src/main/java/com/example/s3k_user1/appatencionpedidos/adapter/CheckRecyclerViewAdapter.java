package com.example.s3k_user1.appatencionpedidos.adapter;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.s3k_user1.appatencionpedidos.CheckoutActivity;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;
import com.example.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.example.s3k_user1.appatencionpedidos.ui.navegacioninicial.FragmentoCategorias;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewHolder> {

    private Context context;

    private List<Comida> mProductObject;
    private MySharedPreference sharedPreference;

    private int cartProductNumber = 0;

    public CheckRecyclerViewAdapter(Context context, List<Comida> mProductObject) {
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

    private List<Comida> convertObjectArrayToListObject(Comida[] allProducts){
        List<Comida> mProduct = new ArrayList<Comida>();
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
        Comida[] storedProducts = gson.fromJson(productsInCart, Comida[].class);

        final List<Comida> allNewProduct = convertObjectArrayToListObject(storedProducts);




        //get product quantity
        holder.quantity.setText("1");
        holder.imagen_product.setImageResource(mProductObject.get(position).getIdDrawable());
        holder.productName.setText(mProductObject.get(position).getNombre());
        holder.productPrice.setText(String.valueOf(mProductObject.get(position).getPrecio()) + " $");

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
                ActivityCompat.invalidateOptionsMenu(FragmentoCategorias.activitydelFragmento);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mProductObject.size();
    }
}

