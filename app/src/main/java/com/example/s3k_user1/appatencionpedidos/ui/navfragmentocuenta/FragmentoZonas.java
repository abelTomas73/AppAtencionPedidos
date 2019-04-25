package com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.model.Zona;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoZonas extends Fragment {


    public FragmentoZonas() {
        // Required empty public constructor
    }

    List<Zona> zonaList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragmento_zonas, container, false);
        zonaList = new ArrayList<>();
        zonaList.add(new Zona("Zona 1"));
        zonaList.add(new Zona("Zona 2"));
        zonaList.add(new Zona("Zona 3"));
        zonaList.add(new Zona("Zona 4"));
        zonaList.add(new Zona("Zona 5"));
        //zonaList.add(new Zona("The Vegitarian"));

        RecyclerView myrv = view.findViewById(R.id.recyclerview_id);
        AdaptadorZonas myAdapter;

        int mNoOfColumns = Utils.calculateNoOfColumns(getContext(),110);

        myAdapter = new AdaptadorZonas(getContext(),zonaList);
        myrv.setLayoutManager(new GridLayoutManager(getContext(),mNoOfColumns));
        myrv.setAdapter(myAdapter);

        return  view;
    }

}
