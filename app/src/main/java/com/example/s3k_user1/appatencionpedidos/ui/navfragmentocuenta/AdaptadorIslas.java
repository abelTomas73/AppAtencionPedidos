package com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;

import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdaptadorIslas
        extends RecyclerView.Adapter<AdaptadorIslas.ViewHolder> {


    private final List<Comida> items;

    private Gson gson;
    public int aumentar =0;
    private MySharedPreference sharedPreference;
    private int cartProductNumber = 0;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;
        public Button agregar_item_al_carrito;



        public ViewHolder(View v) {
            super(v);
            //sharedPreference = new MySharedPreference(v.getContext());

            agregar_item_al_carrito = v.findViewById(R.id.agregar_item_al_carrito);

            PushDownAnim.setPushDownAnimTo(agregar_item_al_carrito)
                    .setScale(PushDownAnim.MODE_SCALE,0.89F);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
        }
    }


    public AdaptadorIslas(List<Comida> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AdaptadorIslas.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_categorias, viewGroup, false);
        return new AdaptadorIslas.ViewHolder(v);
    }

    // DATOS NUEVOS DEL CARRITO
    private List<Comida> convertObjectArrayToListObject(Comida[] allProducts){
        List<Comida> mProduct = new ArrayList<Comida>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }
    //FIN
    @Override
    public void onBindViewHolder(final AdaptadorIslas.ViewHolder viewHolder, int i) {
        final Comida item = items.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                //.placeholder(R.drawable.load)
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText("$" + item.getPrecio());

        viewHolder.agregar_item_al_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),  viewHolder.nombre.getText() +"Isla elegida", Toast.LENGTH_SHORT).show();


            }
        });

    }


}


