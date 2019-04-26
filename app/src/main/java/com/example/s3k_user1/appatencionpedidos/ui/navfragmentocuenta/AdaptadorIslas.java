package com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.example.s3k_user1.appatencionpedidos.model.Isla;
import com.example.s3k_user1.appatencionpedidos.model.Isla;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdaptadorIslas extends RecyclerView.Adapter<AdaptadorIslas.MyViewHolder> implements Filterable {

    private Context mContext ;
    private List<Isla> mDataIsla ;

    private List<Isla> mDataIslaListFull;

    public AdaptadorIslas(Context mContext, List<Isla> mDataIsla) {
        this.mContext = mContext;
        this.mDataIsla = mDataIsla;

        mDataIslaListFull = new ArrayList<>(mDataIsla);
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


        holder.tv_Isla_title.setText(mDataIsla.get(position).getNombre());
        holder.img_Isla_thumbnail.setImageResource(R.drawable.islas);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



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
        ImageView img_Isla_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView,int width) {
            super(itemView);

            tv_Isla_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_Isla_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
//            cardView.getLayoutParams().width = width-200;

        }
    }


}