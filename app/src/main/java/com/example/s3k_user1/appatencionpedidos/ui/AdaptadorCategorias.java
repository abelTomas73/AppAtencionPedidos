package com.example.s3k_user1.appatencionpedidos.ui;

import android.support.v4.app.ActivityCompat;
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
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;
import com.example.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoCategorias;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Adaptador para comidas usadas en la sección "Categorías"
 */
public class AdaptadorCategorias
        extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolder> {


    private final List<Comida> items;

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
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
        }
    }


    public AdaptadorCategorias(List<Comida> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_categorias, viewGroup, false);
        return new ViewHolder(v);
    }

    // DATOS NUEVOS DEL CARRITO
    private List<Comida> convertObjectArrayToListObject(Comida[] allProducts){
        List<Comida> mProduct = new ArrayList<Comida>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }
    //FIN
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Comida item = items.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdDrawable())
                .centerCrop()
                //.placeholder(R.drawable.load)
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.precio.setText("$" + item.getPrecio());

        viewHolder.agregar_item_al_carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),  viewHolder.nombre.getText() +" agregado al Pedido", Toast.LENGTH_SHORT).show();

//
//                Menu menu;
//                menu = ActividadPrincipal.menuclasprincipal;
//                MenuItem item = menu.findItem(R.id.action_carrito);
//
//                // Obtener drawable del item
//                LayerDrawable icon = (LayerDrawable) item.getIcon();
//
//                aumentar++;
//                // Actualizar el contador
//                Utils.setBadgeCount(v.getContext(), icon, aumentar);

                sharedPreference = new MySharedPreference(ActividadPrincipal.contextoAcPrincipal);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String stringObjectRepresentation = gson.toJson(item);
                final Comida singleProduct = gson.fromJson(stringObjectRepresentation, Comida.class);

                String productsFromCart = sharedPreference.retrieveProductFromCart();
                if(productsFromCart.equals("")){
                    List<Comida> cartProductList = new ArrayList<Comida>();
                    cartProductList.add(singleProduct);
                    String cartValue = gson.toJson(cartProductList);
                    sharedPreference.addProductToTheCart(cartValue);
                    cartProductNumber = cartProductList.size();
                }else{
                    String productsInCart = sharedPreference.retrieveProductFromCart();
                    Comida[] storedProducts = gson.fromJson(productsInCart, Comida[].class);

                    List<Comida> allNewProduct = convertObjectArrayToListObject(storedProducts);
                    allNewProduct.add(singleProduct);
                    String addAndStoreNewProduct = gson.toJson(allNewProduct);
                    sharedPreference.addProductToTheCart(addAndStoreNewProduct);
                    cartProductNumber = allNewProduct.size();
                }

                sharedPreference.addProductCount(cartProductNumber);
                ActivityCompat.invalidateOptionsMenu(FragmentoCategorias.activitydelFragmento);
                //invalidateCart();
            }
        });

    }


}