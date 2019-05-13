package com.software3000.s3k_user1.appatencionpedidos.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Zona {
    //TODO LINK JSON TO GSON http://www.jsonschema2pojo.org/

    //TODO SQL https://www.csvjson.com/csv2json
    @SerializedName("CodZona")
    @Expose
    private long codZona;
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

    public Zona() {

    }
    public Zona(String nombre) {

        this.nombre = nombre;

    }

    public long getCodZona() {
        return codZona;
    }

    public void setCodZona(long codZona) {
        this.codZona = codZona;
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

}