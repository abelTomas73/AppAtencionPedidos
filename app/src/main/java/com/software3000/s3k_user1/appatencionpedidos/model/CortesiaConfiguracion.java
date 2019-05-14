package com.software3000.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CortesiaConfiguracion {
    @SerializedName("codCortesiaConfiguracion")
    @Expose
    private int codCortesiaConfiguracion;

    @SerializedName("tipo")
    @Expose
    private int tipo;

    @SerializedName("nombre")
    @Expose
    private String nombre;


    @SerializedName("estado")
    @Expose
    private int estado;

    @SerializedName("fechaRegistro")
    @Expose
    private String fechaRegistro;

    public CortesiaConfiguracion() {
    }

    public CortesiaConfiguracion(int codCortesiaConfiguracion, int tipo, String nombre, int estado, String fechaRegistro) {
        this.codCortesiaConfiguracion = codCortesiaConfiguracion;
        this.tipo = tipo;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public int getCodCortesiaConfiguracion() {
        return codCortesiaConfiguracion;
    }

    public void setCodCortesiaConfiguracion(int codCortesiaConfiguracion) {
        this.codCortesiaConfiguracion = codCortesiaConfiguracion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
