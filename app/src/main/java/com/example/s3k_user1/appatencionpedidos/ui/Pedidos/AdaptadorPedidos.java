package com.example.s3k_user1.appatencionpedidos.ui.Pedidos;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.card.MaterialCardView;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.example.s3k_user1.appatencionpedidos.model.CortesiaPedido;
import com.example.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class AdaptadorPedidos extends RecyclerView.Adapter<AdaptadorPedidos.MyViewHolder> implements Filterable {

    private Context mContext ;
    private List<CortesiaPedido> mDataCortesiaPedido ;

    private List<CortesiaPedido> mDataCortesiaPedidoListFull;
    private int row_index=-1;

    private MySharedPreference sharedPreference;

    public AdaptadorPedidos(Context mContext, List<CortesiaPedido> mDataCortesiaPedido) {
        this.mContext = mContext;
        this.mDataCortesiaPedido = mDataCortesiaPedido;

        this.mDataCortesiaPedidoListFull = new ArrayList<>();
        this.mDataCortesiaPedidoListFull.addAll(mDataCortesiaPedido);
    }
    public void updateSearchedList() {

        mDataCortesiaPedidoListFull.addAll(mDataCortesiaPedido);
    }
    @Override
    public AdaptadorPedidos.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        int width = parent.getMeasuredWidth() / 2;
        view = mInflater.inflate(R.layout.cardview_item_maquina,parent,false);
        return new AdaptadorPedidos.MyViewHolder(view,width);
    }

    @Override
    public void onBindViewHolder(AdaptadorPedidos.MyViewHolder holder, final int position) {
        final CortesiaPedido maquinaE = mDataCortesiaPedido.get(position);

        holder.tv_CortesiaPedido_title.setText(mDataCortesiaPedido.get(position).getCodMaq());
        holder.img_CortesiaPedido_thumbnail.setImageResource(R.drawable.maquinas);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreference = new MySharedPreference(ActividadPrincipal.contextoAcPrincipal);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

//                FragmentoMaquinas.MAQUINAELEGIDA = maquinaE;
//                sharedPreference.guardarPreferenciaCortesiaPedido(FragmentoMaquinas.MAQUINAELEGIDA);
//
//
//                Toasty.success(mContext, FragmentoMaquinas.MAQUINAELEGIDA.getCodMaq()+" elegida", Toast.LENGTH_SHORT, true).show();


                row_index=position;
                notifyDataSetChanged();

            }
        });
        if(row_index==position){
            holder.cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_itemzona_selected));
//            holder.tv_CortesiaPedido_title.setTextColor(Color.parseColor("#
            holder.tv_CortesiaPedido_title.setTypeface(Typeface.DEFAULT_BOLD);
            holder.tv_CortesiaPedido_title.setTextColor(ContextCompat.getColor(mContext,R.color.white));

            holder.descp_maquinas.setTypeface(Typeface.DEFAULT);
            holder.descp_maquinas.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }
        else
        {
            holder.cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_itemzona));
//            holder.tv_CortesiaPedido_title.setTextColor(Color.parseColor("#000000"));
            holder.tv_CortesiaPedido_title.setTypeface(Typeface.DEFAULT);
            holder.tv_CortesiaPedido_title.setTextColor(ContextCompat.getColor(mContext,R.color.primaryColor));

            holder.descp_maquinas.setTypeface(Typeface.DEFAULT);
            holder.descp_maquinas.setTextColor(ContextCompat.getColor(mContext,R.color.color_descripcion));
        }


    }

    @Override
    public int getItemCount() {
        return mDataCortesiaPedido.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CortesiaPedido> filteredList = new ArrayList<>();
            //filtros recibe el texto si y filtra
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mDataCortesiaPedidoListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CortesiaPedido item : mDataCortesiaPedidoListFull) {
                    if (item.getCodMaq().toLowerCase().contains(filterPattern)) {
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
            mDataCortesiaPedido.clear();
            mDataCortesiaPedido.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_CortesiaPedido_title;
        TextView descp_maquinas;
        ImageView img_CortesiaPedido_thumbnail;
        MaterialCardView cardView ;

        public MyViewHolder(View itemView,int width) {
            super(itemView);

            tv_CortesiaPedido_title = itemView.findViewById(R.id.book_title_id) ;
            descp_maquinas = itemView.findViewById(R.id.descp_maquinas) ;
            img_CortesiaPedido_thumbnail = itemView.findViewById(R.id.book_img_id);
            cardView = itemView.findViewById(R.id.cardview_item_maquina);
//            cardView.getLayoutParams().width = width-200;

        }
    }


}
