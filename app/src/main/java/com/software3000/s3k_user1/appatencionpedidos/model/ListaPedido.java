package com.software3000.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaPedido {

    @SerializedName("codCortesiaProducto")
    @Expose
    private int codCortesiaProducto;
    @SerializedName("tipoCortesia")
    @Expose
    private int tipoCortesia;
    @SerializedName("codcortesiaTipo")
    @Expose
    private int codcortesiaTipo;
    @SerializedName("nombreTipo")
    @Expose
    private String nombreTipo;
    @SerializedName("nombreProducto")
    @Expose
    private String nombreProducto;
    @SerializedName("descripcionProducto")
    @Expose
    private String descripcionProducto;
    @SerializedName("nombreArchivo")
    @Expose
    private String nombreArchivo;

    /**
     * No args constructor for use in serialization
     *
     */
    public ListaPedido() {
    }

    /**
     *
     * @param descripcionProducto
     * @param nombreTipo
     * @param nombreArchivo
     * @param codCortesiaProducto
     * @param tipoCortesia
     * @param nombreProducto
     * @param codcortesiaTipo
     */
    public ListaPedido(int codCortesiaProducto, int tipoCortesia, int codcortesiaTipo, String nombreTipo, String nombreProducto, String descripcionProducto, String nombreArchivo) {
        super();
        this.codCortesiaProducto = codCortesiaProducto;
        this.tipoCortesia = tipoCortesia;
        this.codcortesiaTipo = codcortesiaTipo;
        this.nombreTipo = nombreTipo;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.nombreArchivo = nombreArchivo;
    }

    public int getCodCortesiaProducto() {
        return codCortesiaProducto;
    }

    public void setCodCortesiaProducto(int codCortesiaProducto) {
        this.codCortesiaProducto = codCortesiaProducto;
    }

    public int getTipoCortesia() {
        return tipoCortesia;
    }

    public void setTipoCortesia(int tipoCortesia) {
        this.tipoCortesia = tipoCortesia;
    }

    public int getCodcortesiaTipo() {
        return codcortesiaTipo;
    }

    public void setCodcortesiaTipo(int codcortesiaTipo) {
        this.codcortesiaTipo = codcortesiaTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

}