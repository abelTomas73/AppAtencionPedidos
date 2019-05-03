package com.example.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Isla {

    @SerializedName("CodIsla")
    @Expose
    private long codIsla;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("UsuarioID")
    @Expose
    private long usuarioID;
    @SerializedName("FechaRegistro")
    @Expose
    private String fechaRegistro;
    @SerializedName("FechaModificacion")
    @Expose
    private String fechaModificacion;
    @SerializedName("activo")
    @Expose
    private long activo;
    @SerializedName("estado")
    @Expose
    private long estado;
    @SerializedName("CodZona")
    @Expose
    private long codZona;



    @SerializedName("NombreZona")
    @Expose
    private String nombreZona;
    public  Isla(){

    }
    public Isla(long codIsla, String nombre, long usuarioID, String fechaRegistro, String fechaModificacion, long activo, long estado, long codZona, String nombreZona) {
        this.codIsla = codIsla;
        this.nombre = nombre;
        this.usuarioID = usuarioID;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.activo = activo;
        this.estado = estado;
        this.codZona = codZona;
        this.nombreZona = nombreZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public Isla(String nombre){
        this.nombre=nombre;
    }
    public long getCodIsla() {
        return codIsla;
    }

    public void setCodIsla(long codIsla) {
        this.codIsla = codIsla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public long getActivo() {
        return activo;
    }

    public void setActivo(long activo) {
        this.activo = activo;
    }

    public long getEstado() {
        return estado;
    }

    public void setEstado(long estado) {
        this.estado = estado;
    }

    public long getCodZona() {
        return codZona;
    }

    public void setCodZona(long codZona) {
        this.codZona = codZona;
    }

}