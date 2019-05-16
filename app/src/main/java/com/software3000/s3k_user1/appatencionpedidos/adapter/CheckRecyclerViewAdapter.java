package com.software3000.s3k_user1.appatencionpedidos.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.software3000.s3k_user1.appatencionpedidos.CheckoutActivity;
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.software3000.s3k_user1.appatencionpedidos.model.ComboDetalle;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaCombo;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaProductos;

import com.software3000.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.software3000.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.software3000.s3k_user1.appatencionpedidos.ui.CheckOut.AdaptadorComboDetalles;
import com.software3000.s3k_user1.appatencionpedidos.ui.Pedidos.AdaptadorPedidosDetalles;
import com.software3000.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewHolder> {

    private Context context;

    private List<CortesiaProductos> mProductObject;
    private MySharedPreference sharedPreference;

    private int cartProductNumber = 0;

    private List<ComboDetalle> ComboDetallesList;

    private int row_index=-1;
    View view ;
    public CheckRecyclerViewAdapter(Context context, List<CortesiaProductos> mProductObject) {
        this.context = context;
        this.mProductObject = mProductObject;
    }

    @Override
    public CheckRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.check_layout,parent,false);

//        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_layout, parent, false);
        final CheckRecyclerViewHolder productHolder = new CheckRecyclerViewHolder(view);
        productHolder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=productHolder.getAdapterPosition();
                //Toast.makeText(context,"Item at position "+position+" deleted",Toast.LENGTH_SHORT).show();
                //mProductObject.remove(position);
                notifyDataSetChanged();
            }
        });

        return productHolder;
    }
    // inici mismo meth

    private List<CortesiaProductos> convertObjectArrayToListObject(CortesiaProductos[] allProducts){
        List<CortesiaProductos> mProduct = new ArrayList<CortesiaProductos>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }
    private List<CortesiaCombo> convertObjectArrayToListObjectCombo(CortesiaCombo[] allCombos){
        List<CortesiaCombo> mCombo = new ArrayList<>();
        Collections.addAll(mCombo, allCombos);
        return mCombo;
    }
    // fin mis meth

    public String ObtenerIp(){

        SharedPreferences sharedPreferences =context.getSharedPreferences("Protocol", Context.MODE_PRIVATE);
        String ip =sharedPreferences.getString("ip","");
        return ip ;
    }

    public void ListarCombosxCodCombo(final int comboid) {

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

//                                if (comboDetalle.getCodCortesiaProductos()==comboid){
//
//
//                                    ComboDetallesList.add(comboDetalle);
//                                    openDialog();
//                                }
                                ComboDetallesList.add(comboDetalle);

                            }

                            openDialog();
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
                params.put("codCombo", String.valueOf(comboid));
                return params;
            }
        };
        //AppSin
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

    public void openDialog() {
        RecyclerView reciclador;
        Dialog myDialogIP = new Dialog(context);
        //myDialogIP = new Dialog(LoginActivity.this);
        myDialogIP.setContentView(R.layout.dialog_detallescombos_checkout);
//        CargarReferenciaIp();

        reciclador =  myDialogIP.findViewById(R.id.pedidosdetallesCheckoutlist);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        reciclador.setLayoutManager(layoutManager);
        AdaptadorComboDetalles adaptador = new AdaptadorComboDetalles(context, ComboDetallesList);

        reciclador.setAdapter(adaptador);
        myDialogIP.show();
    }

    @Override
    public void onBindViewHolder(final CheckRecyclerViewHolder holder, final int position) {
        sharedPreference = new MySharedPreference(ActividadPrincipal.contextoAcPrincipal);
        final CortesiaProductos proE = mProductObject.get(position);
        GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        String productsInCart = sharedPreference.retrieveProductFromCart();

        CortesiaProductos[] storedProducts = gson.fromJson(productsInCart, CortesiaProductos[].class);
        final List<CortesiaProductos> allNewProduct = convertObjectArrayToListObject(storedProducts);

        holder.quantity.setText("1");
        if (!mProductObject.get(position).getArchivo64String().equals(""))
        {
            byte[] bytearray = Base64.decode(mProductObject.get(position).getArchivo64String(), 0);
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytearray);
            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
            holder.imagen_product.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 192,192, false));

        }else{
            String tipo="0";
            holder.imagen_product.setImageResource(R.drawable.ingredients);
        }


        holder.linear_producto_nombre_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (proE.getArchivo64String().equals("")){//si no tiene imagenstring es un combo
                    int cortesiaidCombo = 0;
                    cortesiaidCombo =proE.getCodCortesiaProductos();
                    ComboDetallesList = new ArrayList<>();
                    ListarCombosxCodCombo(cortesiaidCombo);
                    row_index=position;
                    notifyDataSetChanged();
                }

            }
        });
//        holder.imagen_product.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 192,192, false));

//        holder.imagen_product.setImageResource(R.drawable.ingredients);
        holder.productName.setText(mProductObject.get(position).getNombre());
        holder.productPrice.setText("");

        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sharedPreference.deleteAllProductsFromTheCart();

                //se remueve el producto elegido (indice ) y reutilizar el metodo
                // .addProductToTheCart
                allNewProduct.remove(position);


                String addAndStoreNewProduct = gson.toJson(allNewProduct);

                //sharedPreference.deleteProductfromTheCart(addAndStoreNewProduct);

                Toast.makeText(context, "Item removido de la carta", Toast.LENGTH_LONG).show();

                mProductObject.remove(holder.getAdapterPosition());

                notifyItemRemoved(holder.getAdapterPosition());

                addAndStoreNewProduct = gson.toJson(mProductObject);
                sharedPreference.addProductToTheCart(addAndStoreNewProduct);

                cartProductNumber = mProductObject.size();
                //notifyItemChanged(holder.getAdapterPosition());


                //CheckoutActivity.actualizarSubtotal(mProductObject);

                sharedPreference.addProductCount(cartProductNumber);
                ActivityCompat.invalidateOptionsMenu(FragmentoInicio.activitydelFragmento);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mProductObject.size();
    }
}

