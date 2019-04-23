package com.example.s3k_user1.appatencionpedidos.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;

import java.util.ArrayList;
import java.util.List;


public class FragmentoPedidos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;
    SearchView mSearchView;

    List<Comida> CORTESIAS_PEDIDOS_POR_TRAER;
    List<Comida> COMIDAS_POPULARES_COPIA;

    public FragmentoPedidos() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void poblarPedidosPorTraer() {
        CORTESIAS_PEDIDOS_POR_TRAER = new ArrayList<Comida>();

        CORTESIAS_PEDIDOS_POR_TRAER.add(new Comida(1,5, "Maquina", R.drawable.camarones));
        CORTESIAS_PEDIDOS_POR_TRAER.add(new Comida(2,3.2f, "Maquina 2", R.drawable.rosca));
        CORTESIAS_PEDIDOS_POR_TRAER.add(new Comida(3,12f, "Maquina 3", R.drawable.sushi));
        CORTESIAS_PEDIDOS_POR_TRAER.add(new Comida(4,9, "Maquina Sl", R.drawable.sandwich));
        CORTESIAS_PEDIDOS_POR_TRAER.add(new Comida(5,34f, "Maquina S3K", R.drawable.lomo_cerdo));

        //COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_pedidos, container, false);
        poblarPedidosPorTraer();
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);
        COMIDAS_POPULARES_COPIA = new ArrayList<>();
        COMIDAS_POPULARES_COPIA.addAll(CORTESIAS_PEDIDOS_POR_TRAER);
        adaptador = new AdaptadorInicio();
        adaptador = new AdaptadorInicio(CORTESIAS_PEDIDOS_POR_TRAER);
        reciclador.setAdapter(adaptador);

        return view;
    }


}
