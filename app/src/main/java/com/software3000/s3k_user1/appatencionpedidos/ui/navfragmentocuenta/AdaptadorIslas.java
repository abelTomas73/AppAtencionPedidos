package com.software3000.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;

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

import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.model.Isla;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class AdaptadorIslas extends RecyclerView.Adapter<AdaptadorIslas.MyViewHolder> implements Filterable {

    private Context mContext ;
    private List<Isla> mDataIsla ;

    private List<Isla> mDataIslaListFull;
    private int row_index=-1;
    public AdaptadorIslas(Context mContext, List<Isla> mDataIsla) {
        this.mContext = mContext;
        this.mDataIsla = mDataIsla;

        this.mDataIslaListFull = new ArrayList<>();
        this.mDataIslaListFull.addAll(mDataIsla);
    }
    public void updateSearchedList() {
        mDataIslaListFull.clear();
        mDataIslaListFull.addAll(mDataIsla);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        int width = parent.getMeasuredWidth() / 2;
        view = mInflater.inflate(R.layout.cardview_item_isla,parent,false);
        return new MyViewHolder(view,width);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Isla islaE = mDataIsla.get(position);

        holder.tv_Isla_title.setText(mDataIsla.get(position).getNombre());
        holder.img_Isla_thumbnail.setImageResource(R.drawable.islas);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentoIslas.ISLAELEGIDA = islaE;
//                Toast.makeText(mContext, FragmentoIslas.ISLAELEGIDA.getNombre()+" elegida", Toast.LENGTH_SHORT).show();

                Toasty.success(mContext, FragmentoIslas.ISLAELEGIDA.getNombre()+" elegida", Toast.LENGTH_SHORT, true).show();

                row_index=position;
                notifyDataSetChanged();

            }
        });
        if(row_index==position){
            holder.cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_itemzona_selected));
//            holder.tv_Isla_title.setTextColor(Color.parseColor("#ffffff"));
            holder.tv_Isla_title.setTypeface(Typeface.DEFAULT_BOLD);
            holder.tv_Isla_title.setTextColor(ContextCompat.getColor(mContext,R.color.white));

            holder.descp_isla.setTypeface(Typeface.DEFAULT);
            holder.descp_isla.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }
        else
        {
            holder.cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_itemzona));
//            holder.tv_Isla_title.setTextColor(Color.parseColor("#000000"));
            holder.tv_Isla_title.setTypeface(Typeface.DEFAULT);
            holder.tv_Isla_title.setTextColor(ContextCompat.getColor(mContext,R.color.primaryColor));

            holder.descp_isla.setTypeface(Typeface.DEFAULT);
            holder.descp_isla.setTextColor(ContextCompat.getColor(mContext,R.color.color_descripcion));
        }


    }

    @Override
    public int getItemCount() {
        return mDataIsla.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Isla> filteredList = new ArrayList<>();
            //filtros recibe el texto si y filtra
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mDataIslaListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Isla item : mDataIslaListFull) {
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
            mDataIsla.clear();
            mDataIsla.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Isla_title;
        TextView descp_isla;
        ImageView img_Isla_thumbnail;
        MaterialCardView cardView ;

        public MyViewHolder(View itemView,int width) {
            super(itemView);

            tv_Isla_title = itemView.findViewById(R.id.book_title_id) ;
            descp_isla = itemView.findViewById(R.id.descp_isla) ;
            img_Isla_thumbnail = itemView.findViewById(R.id.book_img_id);
            cardView = itemView.findViewById(R.id.cardview_item_isla);
//            cardView.getLayoutParams().width = width-200;

        }
    }


}