package com.software3000.s3k_user1.appatencionpedidos.ui.CheckOut;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.software3000.s3k_user1.appatencionpedidos.model.ComboDetalle;
import com.software3000.s3k_user1.appatencionpedidos.model.ComboDetalle;
import com.software3000.s3k_user1.appatencionpedidos.model.ListadoAnfitrionasPedidos;
import com.software3000.s3k_user1.appatencionpedidos.model.Login;
import com.software3000.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.software3000.s3k_user1.appatencionpedidos.services.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptadorComboDetalles extends RecyclerView.Adapter<AdaptadorComboDetalles.MyViewHolder> {

    private Context mContext ;
    private List<ComboDetalle> mDataComboDetalle ;

    private List<ComboDetalle> mDataComboDetalleListFull;

    private List<ListadoAnfitrionasPedidos> listadoAnfitrionasPedidosList;
    private List<ComboDetalle> listaPedidoList;
    private int row_index=-1;
    View view ;
    private MySharedPreference sharedPreference;

    public AdaptadorComboDetalles(Context mContext, List<ComboDetalle> mDataComboDetalle) {
        this.mContext = mContext;
        this.mDataComboDetalle = mDataComboDetalle;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.combo_detalle_row,parent,false);

        final MyViewHolder productHolder = new MyViewHolder(view);

//        int width = parent.getMeasuredWidth() / 2;

        return productHolder;

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ComboDetalle comboE = mDataComboDetalle.get(position);

        String nombreTipo = comboE.getNombreTipo();

        holder.textview_pedido_product_nombre.setText(mDataComboDetalle.get(position).getNombreProducto());
        holder.tipo_detalle.setText(nombreTipo);


    }

    @Override
    public int getItemCount() {
        return mDataComboDetalle.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textview_pedido_product_nombre;
        TextView tipo_detalle;
        //        ImageView img_CortesiaPedido_thumbnail;
//        MaterialCardView cardView ;
//        Button btn_finalizarPedido;
        public MyViewHolder(View itemView) {
            super(itemView);

            textview_pedido_product_nombre = itemView.findViewById(R.id.textview_pedido_product_nombre) ;
            tipo_detalle = itemView.findViewById(R.id.tipo_detalle) ;


        }
    }
    public String ObtenerIp(){

        SharedPreferences sharedPreferences =mContext.getSharedPreferences("Protocol", Context.MODE_PRIVATE);
        String ip =sharedPreferences.getString("ip","");
        return ip ;
    }

}
