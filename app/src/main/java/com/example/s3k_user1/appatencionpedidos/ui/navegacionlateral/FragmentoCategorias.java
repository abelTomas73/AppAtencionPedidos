package com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.example.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.example.s3k_user1.appatencionpedidos.ui.FragmentoCategoria;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragmento que contiene otros fragmentos anidados para representar las categorías
 * de comidas
 */
public class FragmentoCategorias extends Fragment {
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MySharedPreference sharedPreference;
    private Context viewfragmentcontext;
    public static Activity activitydelFragmento;
    public FragmentoCategorias() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_paginado, container, false);
        setRetainInstance(true);
        if (savedInstanceState == null) {
            insertarTabs(container);

            // Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(viewPager);

            tabLayout.setupWithViewPager(viewPager);
        }

        sharedPreference = new MySharedPreference(ActividadPrincipal.contextoAcPrincipal);
        viewfragmentcontext = ActividadPrincipal.contextoAcPrincipal;

        activitydelFragmento= getActivity();
        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBarLayout = (AppBarLayout) padre.findViewById(R.id.appbar);

        tabLayout = new TabLayout(getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBarLayout.addView(tabLayout);
    }

    private void poblarViewPager(ViewPager viewPager) {
        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(0), getString(R.string.titulo_tab_platillos));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(1), getString(R.string.titulo_tab_bebidas));
        adapter.addFragment(FragmentoCategoria.nuevaInstancia(2), getString(R.string.titulo_tab_postres));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_categorias, menu);

        MenuItem menuItem = menu.findItem(R.id.action_carrito);
        int mCount = sharedPreference.retrieveProductCount();
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();
        //menuItem.setIcon(buildCounterDrawable(mCount, R.drawable.cart));
        Utils.setBadgeCount(viewfragmentcontext, icon, mCount);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //appBarLayout.removeView(tabLayout);
        if(appBarLayout != null){
            appBarLayout.removeView(tabLayout);
        }
    }

    /**
     * Un {@link FragmentStatePagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }
}
