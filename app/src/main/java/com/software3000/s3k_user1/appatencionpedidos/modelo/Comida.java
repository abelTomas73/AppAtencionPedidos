package com.software3000.s3k_user1.appatencionpedidos.modelo;



import com.software3000.s3k_user1.appatencionpedidos.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Comida {
    private int comidaId;
    private float precio;
    private String nombre;
    private int idDrawable;

    public Comida(int comidaId,float precio, String nombre, int idDrawable) {
        this.comidaId= comidaId;
        this.precio = precio;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public static final List<Comida> COMIDAS_POPULARES = new ArrayList<Comida>();
    public static final List<Comida> BEBIDAS = new ArrayList<>();
    public static final List<Comida> POSTRES = new ArrayList<>();
    public static final List<Comida> PLATILLOS = new ArrayList<>();

    static {
        COMIDAS_POPULARES.add(new Comida(1,5, "Maquina", R.drawable.camarones));
        COMIDAS_POPULARES.add(new Comida(2,3.2f, "Maquina 2", R.drawable.rosca));
        COMIDAS_POPULARES.add(new Comida(3,12f, "Maquina 3", R.drawable.sushi));
        COMIDAS_POPULARES.add(new Comida(4,9, "Maquina Sl", R.drawable.sandwich));
        COMIDAS_POPULARES.add(new Comida(5,34f, "Maquina S3K", R.drawable.lomo_cerdo));

        PLATILLOS.add(new Comida(6,5, "Camarones Tismados", R.drawable.camarones));
        PLATILLOS.add(new Comida(7,3.2f, "Rosca Herbárea", R.drawable.rosca));
        PLATILLOS.add(new Comida(8,12f, "Sushi Extremo", R.drawable.sushi));
        PLATILLOS.add(new Comida(9,9, "Sandwich Deli", R.drawable.sandwich));
        PLATILLOS.add(new Comida(10,34f, "Lomo De Cerdo Austral", R.drawable.lomo_cerdo));

        BEBIDAS.add(new Comida(11,3, "Taza de Café", R.drawable.cafe));
        BEBIDAS.add(new Comida(12,12, "Coctel Tronchatoro", R.drawable.coctel));
        BEBIDAS.add(new Comida(13,5, "Jugo Natural", R.drawable.jugo_natural));
        BEBIDAS.add(new Comida(14,24, "Coctel Jordano", R.drawable.coctel_jordano));
        BEBIDAS.add(new Comida(15,30, "Botella Vino Tinto Darius", R.drawable.vino_tinto));

        POSTRES.add(new Comida(16,2, "Postre De Vainilla", R.drawable.postre_vainilla));
        POSTRES.add(new Comida(17,3, "Flan Celestial", R.drawable.flan_celestial));
        POSTRES.add(new Comida(18,2.5f, "Cupcake Festival", R.drawable.cupcakes_festival));
        POSTRES.add(new Comida(19,4, "Pastel De Fresa", R.drawable.pastel_fresa));
        POSTRES.add(new Comida(20,5, "Muffin Amoroso", R.drawable.muffin_amoroso));
    }

    public float getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }
    public int getcomidaId() {
        return comidaId;
    }
}
