package com.example.s3k_user1.appatencionpedidos.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.s3k_user1.appatencionpedidos.CheckoutActivity;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.example.s3k_user1.appatencionpedidos.helpers.SessionManager;
import com.example.s3k_user1.appatencionpedidos.loginSistema.LoginActivity;
import com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoCategorias;
import com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoCuenta;
import com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoProductos;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;

import java.util.HashMap;

public class ActividadPrincipal extends AppCompatActivity {

    private DrawerLayout drawerLayout;
private NavigationView navigationView;
    public int item;
    public static Menu menuclasprincipal;
    public static int cantidadCarrito;

    private MySharedPreference sharedPreference;
    public static Context contextoAcPrincipal;
    public static Activity activitydelAcPrincipal;
    SessionManager session;
    String sesion_usuario = "";

    // id
    String sesion_id = "";

    String sesion_empleado = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        agregarToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
         navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();
        sesion_usuario = user.get(SessionManager.KEY_USUARIO_NOMBRE);
        sesion_id = user.get(SessionManager.KEY_USUARIO_ID);
        sesion_empleado = user.get(SessionManager.KEY_USUARIO_EMPLEADO);

        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
        ((TextView) header.findViewById(R.id.usuario_sesion_navegacion)).setText(sesion_usuario);
        ((TextView) header.findViewById(R.id.nombreusuario_sesion_navegacion)).setText(sesion_empleado);
        sharedPreference = new MySharedPreference(this.getApplicationContext());
        contextoAcPrincipal = this.getApplicationContext();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Frag", item);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        item = savedInstanceState.getInt("Frag");
        seleccionarItem(navigationView.getMenu().getItem(item));
    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.item_inicio:
                fragmentoGenerico = new FragmentoInicio();
                item=0;
                break;
            case R.id.item_cuenta:
                fragmentoGenerico = new FragmentoCuenta();
                item=1;
                break;
            case R.id.item_categorias:
                fragmentoGenerico = new FragmentoCategorias();
                item=2;
                break;
            case R.id.item_productos:
                item=3;
                fragmentoGenerico = new FragmentoProductos();
                break;
            case R.id.item_cerrarsesion:

                session.logoutUser();
                Intent intent1 = new Intent(this,LoginActivity.class);
                startActivity(intent1);
                break;

            case R.id.item_configuracion:
                item=4;
                startActivity(new Intent(this, ActividadConfiguracion.class));
                break;

        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuclasprincipal = menu;
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);

//        MenuItem item = menu.findItem(R.id.action_carrito);
//
//        // Obtener drawable del item
//        LayerDrawable icon = (LayerDrawable) item.getIcon();
//
//        // Actualizar el contador
//        Utils.setBadgeCount(this, icon, 0);

        MenuItem menuItem = menu.findItem(R.id.action_carrito);
        int mCount = sharedPreference.retrieveProductCount();
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();
        //menuItem.setIcon(buildCounterDrawable(mCount, R.drawable.cart));
        Utils.setBadgeCount(this, icon, mCount);
        return true;
    }
    //Agregar carrito nuevo

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        if (item.getItemId() == R.id.action_carrito) {
            Intent checkoutIntent = new Intent(ActividadPrincipal.this, CheckoutActivity.class);
            startActivity(checkoutIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
