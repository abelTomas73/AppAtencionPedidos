package com.software3000.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.FloatRange;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaProductos;

import com.software3000.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;

import com.software3000.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdaptadorArticulos
        extends RecyclerView.Adapter<AdaptadorArticulos.ViewHolder> {


    private final List<CortesiaProductos> items;

    private Gson gson;
    public int aumentar =0;
    private MySharedPreference sharedPreference;
    private int cartProductNumber = 0;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;
        public Button agregar_item_al_carrito;



        public ViewHolder(View v) {
            super(v);
            //sharedPreference = new MySharedPreference(v.getContext());

            agregar_item_al_carrito = v.findViewById(R.id.agregar_item_al_carrito);

            PushDownAnim.setPushDownAnimTo(agregar_item_al_carrito)
                    .setScale(PushDownAnim.MODE_SCALE,0.89F);
            nombre = v.findViewById(R.id.nombre_comida);
            precio =  v.findViewById(R.id.precio_comida);
            imagen = v.findViewById(R.id.miniatura_comida);
        }
    }


    public AdaptadorArticulos(List<CortesiaProductos> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AdaptadorArticulos.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_categorias, viewGroup, false);
        return new AdaptadorArticulos.ViewHolder(v);
    }

    // DATOS NUEVOS DEL CARRITO
    private List<CortesiaProductos> convertObjectArrayToListObject(CortesiaProductos[] allProducts){
        List<CortesiaProductos> mProduct = new ArrayList<CortesiaProductos>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }
    //FIN
    public void setImageBitmap(byte[] byteArray) {


        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(byteArray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
//        imagenSituacion.setImageBitmap(Bitmap.createScaledBitmap(bitmap, imagenSituacion.getWidth(),
//                imagenSituacion.getHeight(), false));

    }

    @Override
    public void onBindViewHolder(final AdaptadorArticulos.ViewHolder viewHolder, int i) {
        final CortesiaProductos item = items.get(i);

//        Glide.with(viewHolder.itemView.getContext())
//                .load(item.getIdDrawable())
//                .centerCrop()
//                //.placeholder(R.drawable.load)
//                .into(viewHolder.imagen);

        byte[] bytearray = Base64.decode(item.getArchivo64String(), 0);

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytearray);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);

        viewHolder.imagen.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 192,
                192, false));

        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText(item.getDescripcion());


        if ( item.getEstadoTurnoValido()==0){
            //viewHolder.imagen.getResources().getColor(R.color.transparent);
            viewHolder.imagen.setAlpha(0.4f);
        }else {
            viewHolder.imagen.setAlpha(1.0f);
        }
        viewHolder.agregar_item_al_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getEstadoTurnoValido()==1){
                    Toast.makeText(v.getContext(),  viewHolder.nombre.getText() +" agregado al Pedido", Toast.LENGTH_SHORT).show();

                    sharedPreference = new MySharedPreference(ActividadPrincipal.contextoAcPrincipal);

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    String stringObjectRepresentation = gson.toJson(item);
                    final CortesiaProductos singleProduct = gson.fromJson(stringObjectRepresentation, CortesiaProductos.class);

                    String productsFromCart = sharedPreference.retrieveProductFromCart();
                    if(productsFromCart.equals("")){
                        List<CortesiaProductos> cartProductList = new ArrayList<CortesiaProductos>();
                        cartProductList.add(singleProduct);
                        String cartValue = gson.toJson(cartProductList);
                        sharedPreference.addProductToTheCart(cartValue);
                        cartProductNumber = cartProductList.size();
                    }else{
                        String productsInCart = sharedPreference.retrieveProductFromCart();
                        CortesiaProductos[] storedProducts = gson.fromJson(productsInCart, CortesiaProductos[].class);

                        List<CortesiaProductos> allNewProduct = convertObjectArrayToListObject(storedProducts);
                        allNewProduct.add(singleProduct);
                        String addAndStoreNewProduct = gson.toJson(allNewProduct);
                        sharedPreference.addProductToTheCart(addAndStoreNewProduct);
                        cartProductNumber = allNewProduct.size();
                    }

                    sharedPreference.addProductCount(cartProductNumber);
                    ActivityCompat.invalidateOptionsMenu(FragmentoInicio.activitydelFragmento);
                }else{
                    Toast.makeText(v.getContext(),  "Producto "+item.getNombre()+" no apto en este turno", Toast.LENGTH_SHORT).show();
                }

                //invalidateCart();
            }
        });

    }


}


