package com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.model.Zona;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;

import java.util.ArrayList;
import java.util.List;


/**
 * Adaptador para comidas usadas en la sección "Categorías"
 */
public class AdaptadorZonas extends RecyclerView.Adapter<AdaptadorZonas.MyViewHolder> implements Filterable {

    private Context mContext ;
    private List<Zona> mData ;

    private List<Zona> mDataListFull;
    private int row_index=-1;
    public AdaptadorZonas(Context mContext, List<Zona> mData) {
        this.mContext = mContext;
        this.mData = mData;

        mDataListFull = new ArrayList<>(mData);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        int width = parent.getMeasuredWidth() / 2;
        view = mInflater.inflate(R.layout.cardview_item_zona,parent,false);
        return new MyViewHolder(view,width);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Zona zonaE = mData.get(position);

        holder.tv_Zona_title.setText(mData.get(position).getNombre());
        holder.img_Zona_thumbnail.setImageResource(R.drawable.zonas);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentoZonas.ZONAELEGIDA = new Zona();
                FragmentoZonas.ZONAELEGIDA = zonaE;
                Toast.makeText(mContext, FragmentoZonas.ZONAELEGIDA.getNombre()+" elegida", Toast.LENGTH_SHORT).show();
                row_index=position;
                notifyDataSetChanged();
//                holder.cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_itemzona_selected));

//                Intent intent = new Intent(mContext,Zona_Activity.class);
//
//                // passing data to the Zona activity
//                intent.putExtra("Title",mData.get(position).getTitle());
//                intent.putExtra("Description",mData.get(position).getDescription());
//                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
//                // start the activity
//                mContext.startActivity(intent);

            }
        });
        if(row_index==position){
            holder.cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_itemzona_selected));
            //holder.tv1.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_itemzona));
//            holder.tv1.setTextColor(Color.parseColor("#000000"));
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Zona> filteredList = new ArrayList<>();
            //filtros recibe el texto si y filtra
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mDataListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Zona item : mDataListFull) {
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
            mData.clear();
            mData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Zona_title;
        ImageView img_Zona_thumbnail;
        MaterialCardView cardView ;

        public MyViewHolder(View itemView,int width) {
            super(itemView);

            tv_Zona_title = itemView.findViewById(R.id.book_title_id) ;
            img_Zona_thumbnail = itemView.findViewById(R.id.book_img_id);
            cardView = itemView.findViewById(R.id.cardview_item_zona);
//            cardView.getLayoutParams().width = width-200;


        }
    }


}