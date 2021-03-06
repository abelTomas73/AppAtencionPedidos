package com.software3000.s3k_user1.appatencionpedidos.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.helpers.SharePreferencesCheckOut;
import com.software3000.s3k_user1.appatencionpedidos.modelo.Comida;
import com.software3000.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.software3000.s3k_user1.appatencionpedidos.ui.navfragmentocuenta.FragmentoArticulos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador para mostrar las comidas más pedidas en la sección "Inicio"
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder>implements Filterable {

    private List<Comida> exampleList;
    private List<Comida> exampleListFull;
    private Context mContext ;
    private SharePreferencesCheckOut sharedPreference;
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Comida> filteredList = new ArrayList<>();
            //filtros recibe el texto si y filtra
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Comida item : exampleListFull) {
                    if (item.getNombre().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
        }
    }

    public AdaptadorInicio() {
    }

    public AdaptadorInicio(Context mContext,List<Comida> exampleList) {
        this.mContext = mContext;
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }
    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_inicio, parent, false);

        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {

        sharedPreference = new SharePreferencesCheckOut(ActividadPrincipal.contextoAcPrincipal);
        GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        final Comida item = exampleList.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .apply(RequestOptions.centerCropTransform())
                .into(viewHolder.imagen);
//        viewHolder.nombre.setText("M-" +item.getPrecio());
        viewHolder.precio.setText( item.getNombre());

        viewHolder.imagen.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                //sharedPreference.seleccionarMaquina(item);
//                Fragment fragment = new FragmentoMaquinas();
//                FragmentTransaction transaction = mContext
//                transaction.replace(R.id.contenedor_principal, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
                return true;
            }
        });
        viewHolder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentoArticulos fragment2 = new FragmentoArticulos();
//                Bundle args = new Bundle();
//                args.putString("idTipo", item.getcomidaId()+"");
//                fragment2.setArguments(args);
//                FragmentTransaction transaction2 = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
//                transaction2.add(R.id.container, fragment2).commit();

                Fragment fragment = new FragmentoArticulos();
                FragmentTransaction transaction = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putString("categoria_idtipo", item.getcomidaId()+"");
                args.putString("categoria_nombre", item.getNombre()+"");
                fragment.setArguments(args);
                transaction.replace(R.id.contenedor_principal, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

//                String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
//                FragmentTransaction transaction2 = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
//                FragmentoArticulos f = getSupportFragmentManager().findFragmentByTag(tag);
//                f.displayReceivedData(message);
            }
        });
    }


}