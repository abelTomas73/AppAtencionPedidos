package com.software3000.s3k_user1.appatencionpedidos.ui.Pedidos;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
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
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.software3000.s3k_user1.appatencionpedidos.model.ComboDetalle;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaPedido;
import com.software3000.s3k_user1.appatencionpedidos.model.ListaPedido;
import com.software3000.s3k_user1.appatencionpedidos.model.ListadoAnfitrionasPedidos;
import com.software3000.s3k_user1.appatencionpedidos.model.Login;
import com.software3000.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.software3000.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptadorPedidos extends RecyclerView.Adapter<AdaptadorPedidos.MyViewHolder> implements Filterable {

    private Context mContext ;
    private List<CortesiaPedido> mDataCortesiaPedido ;

    private List<CortesiaPedido> mDataCortesiaPedidoListFull;
    private List<ComboDetalle> ComboDetallesList;
    private List<ListadoAnfitrionasPedidos> listadoAnfitrionasPedidosList;
    private List<ListaPedido> listaPedidoList;
    private int row_index=-1;
    View view ;
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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_maquina,parent,false);

        final MyViewHolder productHolder = new MyViewHolder(view);
        productHolder.btn_finalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
            }
        });
//        int width = parent.getMeasuredWidth() / 2;

        return productHolder;
    }

    public void ListarCombosxCodCombo() {
        ComboDetallesList = new ArrayList<>();
        ComboDetallesList.clear();

//        ListarCombosxCodCombo?codCombo=4
        String URls =  ObtenerIp()+"/Cortesias/ListarCombosxCodCombo";

        StringRequest stringRequest = new StringRequest  (Request.Method.POST, URls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressBar.setVisibility(View.GONE);
//                        progressDialog.dismiss();

                        JSONArray jsondata=null;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            jsondata = jsonObject.getJSONArray("data");
                            Gson gson = new Gson();


                            for (int i = 0; i < jsondata.length(); i++) {
                                JSONObject jsonObject2 = jsondata.getJSONObject(i);
                                ComboDetalle comboDetalle = new ComboDetalle();

                                comboDetalle= gson.fromJson(jsonObject2.toString(), ComboDetalle.class);

                                ComboDetallesList.add(comboDetalle);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //progressDialog.dismiss();
                        //swipeRefreshLayout.setRefreshing(false);
//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//
//                            DynamicToast.makeWarning(getBaseContext(), "Error: Tiempo de Respuesta en búsqueda DNI ", Toast.LENGTH_LONG).show();
//                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("codCombo", String.valueOf(2));
                return params;
            }
        };
        //AppSin
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);

    }

    public void ListadoAnfitrionasPedidos(final int codCortePedido) {

        listadoAnfitrionasPedidosList.clear();

//        ListarCombosxCodCombo?codCombo=4
        String URls =  ObtenerIp()+"/Cortesias/ListadoAnfitrionasPedidos";

        StringRequest stringRequest = new StringRequest  (Request.Method.POST, URls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressBar.setVisibility(View.GONE);
//                        progressDialog.dismiss();

                        JSONArray jsondata=null;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            jsondata = jsonObject.getJSONArray("data");
                            Gson gson = new Gson();


                            for (int i = 0; i < jsondata.length(); i++) {
                                JSONObject jsonObject2 = jsondata.getJSONObject(i);
                                ListadoAnfitrionasPedidos anfitrionasPedidos = new ListadoAnfitrionasPedidos();
                                ListaPedido pedido = new ListaPedido();
                                anfitrionasPedidos= gson.fromJson(jsonObject2.toString(), ListadoAnfitrionasPedidos.class);
                                if (anfitrionasPedidos.getCodPedido()==codCortePedido){

                                    listadoAnfitrionasPedidosList.add(anfitrionasPedidos);

                                    listaPedidoList =anfitrionasPedidos.getListaPedido();
                                    openDialog();
                                }

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //progressDialog.dismiss();
                        //swipeRefreshLayout.setRefreshing(false);
//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//
//                            DynamicToast.makeWarning(getBaseContext(), "Error: Tiempo de Respuesta en búsqueda DNI ", Toast.LENGTH_LONG).show();
//                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("codCombo", String.valueOf(2));
                return params;
            }
        };
        //AppSin
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);

    }
    public void openDialog() {
        RecyclerView reciclador;
        Dialog myDialogIP = new Dialog(mContext);
        //myDialogIP = new Dialog(LoginActivity.this);
        myDialogIP.setContentView(R.layout.dialog_detallescombos);
//        CargarReferenciaIp();

        reciclador =  myDialogIP.findViewById(R.id.pedidosdetalleslist);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        reciclador.setLayoutManager(layoutManager);
        AdaptadorPedidosDetalles adaptador = new AdaptadorPedidosDetalles(mContext, listaPedidoList);

        reciclador.setAdapter(adaptador);
        myDialogIP.show();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final CortesiaPedido pedidoE = mDataCortesiaPedido.get(position);

        holder.tv_CortesiaPedido_title.setText(mDataCortesiaPedido.get(position).getCodMaq());
        holder.img_CortesiaPedido_thumbnail.setImageResource(R.drawable.ingredients);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreference = new MySharedPreference(ActividadPrincipal.contextoAcPrincipal);

                listadoAnfitrionasPedidosList = new ArrayList<>();
                listaPedidoList = new ArrayList<>();
                ListadoAnfitrionasPedidos(pedidoE.getCodCortesiaPedido());
                row_index=position;
                notifyDataSetChanged();



            }
        });

        if(pedidoE.getEstado()==1){//pendientes
//            holder.cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bg_itemzona_selected));
////            holder.tv_CortesiaPedido_title.setTextColor(Color.parseColor("#
//            holder.tv_CortesiaPedido_title.setTypeface(Typeface.DEFAULT_BOLD);
//            holder.tv_CortesiaPedido_title.setTextColor(ContextCompat.getColor(mContext,R.color.white));
//
//            holder.descp_maquinas.setTypeface(Typeface.DEFAULT);
//            holder.descp_maquinas.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }
        else if(pedidoE.getEstado()==2)//pedidos preparados
        {
            holder.btn_finalizarPedido.setVisibility(View.VISIBLE);

            holder.cardView.setBackground(ContextCompat.getDrawable(mContext, R.drawable.btn_rounded_color));
//            holder.tv_CortesiaPedido_title.setTextColor(Color.parseColor("#000000"));
            holder.tv_CortesiaPedido_title.setTypeface(Typeface.DEFAULT);
            holder.tv_CortesiaPedido_title.setTextColor(ContextCompat.getColor(mContext,R.color.white));

            holder.descp_maquinas.setTypeface(Typeface.DEFAULT);
            holder.descp_maquinas.setText("Pedido Listo para Recoger");
            holder.descp_maquinas.setTextColor(ContextCompat.getColor(mContext,R.color.white));
        }

        holder.btn_finalizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
//            1 pendientes
//            2 por recoger
//            3 finalizado
            public void onClick(View v) {
                ActualizarEstadoCortesiaPedido(pedidoE.getCodCortesiaPedido(),3, holder);

            }
        });

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
        Button btn_finalizarPedido;
        public MyViewHolder(View itemView) {
            super(itemView);

            tv_CortesiaPedido_title = itemView.findViewById(R.id.book_title_id) ;
            descp_maquinas = itemView.findViewById(R.id.descp_maquinas) ;
            img_CortesiaPedido_thumbnail = itemView.findViewById(R.id.book_img_id);
            cardView = itemView.findViewById(R.id.cardview_item_maquina);
            btn_finalizarPedido = itemView.findViewById(R.id.btn_finalizarPedido);
//            cardView.getLayoutParams().width = width-200;

        }
    }
    public String ObtenerIp(){

        SharedPreferences sharedPreferences =mContext.getSharedPreferences("Protocol", Context.MODE_PRIVATE);
        String ip =sharedPreferences.getString("ip","");
        return ip ;
    }
    public void ActualizarEstadoCortesiaPedido(final int cortesiaPedidoid, final int estado, final AdaptadorPedidos.MyViewHolder holder) {

        String URls =  ObtenerIp()+"/Cortesias/ActualizarEstadoCortesiaPedido";

        StringRequest stringRequest = new StringRequest  (Request.Method.POST, URls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressBar.setVisibility(View.GONE);
//                        progressDialog.dismiss();

                        JSONArray jsondata=null;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("respuesta")) {

                                //Toast.makeText(getApplicationContext(), jsonObject.getString("mensaje"), Toast.LENGTH_SHORT).show();
                                Snackbar.make(view, jsonObject.getString("mensaje"), Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();


                                Gson gson = new Gson();
                                Login login = new Login();

                                login= gson.fromJson(jsonObject.toString(), Login.class);

                                mDataCortesiaPedido.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());

                            }
//                            else {
//                                //Toast.makeText(getApplicationContext(), jsonObject.getString("mensaje"), Toast.LENGTH_SHORT).show();
//                                Snackbar.make(view, jsonObject.getString("mensaje"), Snackbar.LENGTH_LONG)
//                                        .setAction("Action", null).show();
//                            }

//                            adaptador.updateSearchedList();
//                            // refreshing recycler view
//                            adaptador.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //progressDialog.dismiss();
//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//
//                            DynamicToast.makeWarning(getBaseContext(), "Error: Tiempo de Respuesta en búsqueda DNI ", Toast.LENGTH_LONG).show();
//                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cortesiaPedidoid", String.valueOf(cortesiaPedidoid));
                params.put("estado", String.valueOf(estado));
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(mContext).addToRequestQueue(stringRequest);

    }
}
