package com.software3000.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.model.Zona;
import com.software3000.s3k_user1.appatencionpedidos.modelo.Comida;
import com.software3000.s3k_user1.appatencionpedidos.ui.AdaptadorInicio;
import com.software3000.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoCategoriasProductos extends Fragment {

    private List<Zona> zonaList;
    List<Comida> COMIDAS_POPULARES;
    List<Comida> COMIDAS_POPULARES_COPIA;
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;
    private TextView categoriasTitulo;
    public FragmentoCategoriasProductos() {
        // Required empty public constructor
    }

        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void poblarCategoriasCombos() {
        COMIDAS_POPULARES = new ArrayList<Comida>();


        COMIDAS_POPULARES.add(new Comida(1,3.2f, "Sándwich", R.drawable.sandwich));
        COMIDAS_POPULARES.add(new Comida(2,12f, "Bocadito", R.drawable.rosca));
        COMIDAS_POPULARES.add(new Comida(3,9, "Postre", R.drawable.postre_vainilla));
        COMIDAS_POPULARES.add(new Comida(4,34f, "Bebidas Calientes", R.drawable.cafe));
        COMIDAS_POPULARES.add(new Comida(5,5, "Bebidas Sin Alcohol", R.drawable.vino_tinto));
        COMIDAS_POPULARES.add(new Comida(6,5, "Jugo", R.drawable.jugo_natural));
        COMIDAS_POPULARES.add(new Comida(7,5, "Snacks", R.drawable.sushi));
        COMIDAS_POPULARES.add(new Comida(8,5, "Bebidas Con Alcohol", R.drawable.coctel));


        //COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

                View view = inflater.inflate(R.layout.fragmento_categorias_productos, container, false);
        poblarCategoriasCombos();
        reciclador = view.findViewById(R.id.reciclador);

        categoriasTitulo = view.findViewById(R.id.categoriasTitulo);

        String categoriaT = FragmentoInicio.ZONAELEGIDA.getNombre()+
                            " - "+FragmentoIslas.ISLAELEGIDA.getNombre()+
                            " - "+FragmentoMaquinas.MAQUINAELEGIDA.getCodMaq();
        categoriasTitulo.setText(categoriaT);


        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);
        COMIDAS_POPULARES_COPIA = new ArrayList<>();
        COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
        adaptador = new AdaptadorInicio();
//        adaptador = new AdaptadorInicio(COMIDAS_POPULARES);
        adaptador = new AdaptadorInicio(getContext(), COMIDAS_POPULARES);
        reciclador.setAdapter(adaptador);
        return view;

    }

}
