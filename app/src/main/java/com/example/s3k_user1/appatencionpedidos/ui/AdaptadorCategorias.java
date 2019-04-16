package com.example.s3k_user1.appatencionpedidos.ui;

import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;

import java.util.List;

/**
 * Adaptador para comidas usadas en la sección "Categorías"
 */
public class AdaptadorCategorias
        extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolder> {


    private final List<Comida> items;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;
        public ImageView agregar_item_al_carrito;
        public int aumentar =0;
        public ViewHolder(View v) {
            super(v);
            agregar_item_al_carrito = v.findViewById(R.id.agregar_item_al_carrito);

            agregar_item_al_carrito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "P: "+ nombre.getText().toString(), Toast.LENGTH_SHORT).show();

                    Menu menu;
                    menu = ActividadPrincipal.menuclasprincipal;
                    MenuItem item = menu.findItem(R.id.action_carrito);

                    // Obtener drawable del item
                    LayerDrawable icon = (LayerDrawable) item.getIcon();

                    aumentar++;
                    // Actualizar el contador
                    Utils.setBadgeCount(v.getContext(), icon, aumentar);
                }
            });
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
        }
    }


    public AdaptadorCategorias(List<Comida> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_categorias, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Comida item = items.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText("$" + item.getPrecio());



    }


}