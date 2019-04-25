package com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;
import com.example.s3k_user1.appatencionpedidos.ui.AdaptadorCategorias;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoArticulos extends Fragment {

    private RecyclerView reciclador;
    //private LinearLayoutManager layoutManager;
    //private AdaptadorInicio adaptador;
    SearchView mSearchView;

    List<Comida> COMIDAS_POPULARES;
    List<Comida> COMIDAS_POPULARES_COPIA;

    private GridLayoutManager layoutManager;
    private AdaptadorArticulos adaptador;

    public FragmentoArticulos() {
        // Required empty public constructor
    }

    private void poblarArticulos() {
        COMIDAS_POPULARES = new ArrayList<Comida>();

        COMIDAS_POPULARES.add(new Comida(1,5, "Maquina", R.drawable.camarones));
        COMIDAS_POPULARES.add(new Comida(2,3.2f, "Maquina 2", R.drawable.rosca));
        COMIDAS_POPULARES.add(new Comida(3,12f, "Maquina 3", R.drawable.sushi));
        COMIDAS_POPULARES.add(new Comida(4,9, "Maquina Sl", R.drawable.sandwich));
        COMIDAS_POPULARES.add(new Comida(5,34f, "Maquina S3K", R.drawable.lomo_cerdo));

        //COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragmento_articulos, container, false);
        View view = inflater.inflate(R.layout.fragmento_articulos, container, false);
        poblarArticulos();
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
//        layoutManager = new LinearLayoutManager(getActivity());

        int mNoOfColumns = Utils.calculateNoOfColumns(getContext(),180);
        layoutManager = new GridLayoutManager(getActivity(), mNoOfColumns);

        reciclador.setLayoutManager(layoutManager);

        COMIDAS_POPULARES_COPIA = new ArrayList<>();
        COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);

        adaptador = new AdaptadorArticulos(Comida.PLATILLOS);

//        adaptador = new AdaptadorInicio(COMIDAS_POPULARES);
        reciclador.setAdapter(adaptador);
        return view;
    }

}
