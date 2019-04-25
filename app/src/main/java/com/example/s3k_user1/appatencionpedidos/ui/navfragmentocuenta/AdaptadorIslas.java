package com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
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
import com.example.s3k_user1.appatencionpedidos.model.Isla;
import com.example.s3k_user1.appatencionpedidos.model.Zona;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdaptadorIslas extends RecyclerView.Adapter<AdaptadorIslas.MyViewHolder> {

    private Context mContext ;
    private List<Isla> mData ;


    public AdaptadorIslas(Context mContext, List<Isla> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_isla,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_Isla_title.setText(mData.get(position).getNombre());
        holder.img_Isla_thumbnail.setImageResource(R.drawable.islas);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(mContext,Isla_Activity.class);
//
//                // passing data to the Isla activity
//                intent.putExtra("Title",mData.get(position).getTitle());
//                intent.putExtra("Description",mData.get(position).getDescription());
//                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
//                // start the activity
//                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_Isla_title;
        ImageView img_Isla_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_Isla_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_Isla_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}

