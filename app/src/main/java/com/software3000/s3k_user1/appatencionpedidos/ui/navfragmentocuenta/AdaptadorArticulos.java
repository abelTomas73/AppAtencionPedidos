package com.software3000.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaProductos;

import com.software3000.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;

import com.software3000.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.thekhaeng.pushdownanim.PushDownAnim;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        public LinearLayout producto_seleccion;


        public ViewHolder(View v) {
            super(v);
            //sharedPreference = new MySharedPreference(v.getContext());

            producto_seleccion = v.findViewById(R.id.producto_seleccion);
            agregar_item_al_carrito = v.findViewById(R.id.agregar_item_al_carrito);

//            PushDownAnim.setPushDownAnimTo(agregar_item_al_carrito)
//                    .setScale(PushDownAnim.MODE_SCALE,0.89F);
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


        if (item.getArchivo64String()==null){
//            RequestOptions requestOptions = new RequestOptions()
//                    .fitCenter()
//                    .placeholder(R.drawable.loading)
//                    .centerInside()
//                    .error(R.drawable.images_not_available)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .priority(Priority.HIGH)
//                    .dontAnimate()
//                    .dontTransform();

            Glide.with(viewHolder.itemView.getContext())
                    .load(R.drawable.images_not_available)
                    .apply(RequestOptions.centerCropTransform())
                    .into(viewHolder.imagen);



        }else {
            byte[] bytearray = Base64.decode(item.getArchivo64String(), 0);
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bytearray);
            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
            viewHolder.imagen.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 192,
                    192, false));
            viewHolder.imagen.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText(item.getNombreTipo());

        if (item.getEstadoTurnoValido()==1){
            //viewHolder.imagen.getResources().getColor(R.color.transparent);
            //viewHolder.producto_seleccion.getBackground().setAlpha(255);
            viewHolder.imagen.setAlpha(1.0f);
            viewHolder.agregar_item_al_carrito.setText("Agregar al carrito");
        }else if(item.getEstadoTurnoValido()==0){
            //viewHolder.producto_seleccion.getBackground().setAlpha(127);
            viewHolder.imagen.setAlpha(0.4f);
            viewHolder.agregar_item_al_carrito.setText("Producto no disponible en este turno");
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
                        List<CortesiaProductos> cartProductListDuplicados = new ArrayList<CortesiaProductos>();
                        singleProduct.setCantidadPro(1);

                        cartProductList.add(singleProduct);


                        String cartValue = gson.toJson(cartProductList);
                        sharedPreference.addProductToTheCart(cartValue);
                        cartProductNumber = cartProductList.size();
                    }else{
                        String productsInCart = sharedPreference.retrieveProductFromCart();
                        CortesiaProductos[] storedProducts = gson.fromJson(productsInCart, CortesiaProductos[].class);

                        List<CortesiaProductos> allNewProduct = convertObjectArrayToListObject(storedProducts);


                        boolean productoExistenLista = validarProductoExistenLista(allNewProduct,singleProduct);
                        int cantidadProductos= 0;
                        if (productoExistenLista){
                            //singleProduct.setCantidadPro(singleProduct.getCantidadPro()+1);
                            int indiceProductoRepetido = getIndexOf(allNewProduct,singleProduct.getCodCortesiaProductos());
                            int cantidadAnterior =allNewProduct.get(indiceProductoRepetido).getCantidadPro();
                            cantidadAnterior++;
                            allNewProduct.get(indiceProductoRepetido).setCantidadPro(cantidadAnterior);
                            cantidadProductos=sharedPreference.retrieveProductCount()+1;
                        }else {
                            singleProduct.setCantidadPro(1);
                            allNewProduct.add(singleProduct);
                            cantidadProductos=sharedPreference.retrieveProductCount()+1;
                        }



                        String addAndStoreNewProduct = gson.toJson(allNewProduct);
                        sharedPreference.addProductToTheCart(addAndStoreNewProduct);
                        cartProductNumber = cantidadProductos;
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

    private boolean validarProductoExistenLista( List<CortesiaProductos> p_listaProductos, CortesiaProductos p_productoAComparar){
        List<CortesiaProductos> listaMeto = new ArrayList<>();
        boolean productoExisteLista = false;
        for(int i = 0; i < p_listaProductos.size(); i++) {
            int cantidadxProducto=1;
            if(p_productoAComparar.getCodCortesiaProductos() == p_listaProductos.get(i).getCodCortesiaProductos()){

                productoExisteLista=true;
                break;
            }
            listaMeto.add(p_productoAComparar);
        }

        return  productoExisteLista;
    }

    public int getIndexOf(List<CortesiaProductos> list, int cod) {
        int pos = 0;

        for(CortesiaProductos myObj : list) {
            if(cod==(myObj.getCodCortesiaProductos()))
                return pos;
            pos++;
        }

        return -1;
    }
}


