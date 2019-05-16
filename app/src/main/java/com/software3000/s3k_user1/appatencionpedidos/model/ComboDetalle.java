package com.software3000.s3k_user1.appatencionpedidos.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComboDetalle {

    @SerializedName("CodCortesiaComboDetalle")
    @Expose
    private int codCortesiaComboDetalle;
    @SerializedName("CodCortesiaCombo")
    @Expose
    private int codCortesiaCombo;
    @SerializedName("codCortesiaProductos")
    @Expose
    private int codCortesiaProductos;
    @SerializedName("Cantidad")
    @Expose
    private int cantidad;
    @SerializedName("FechaRegistro")
    @Expose
    private String fechaRegistro;
    @SerializedName("estado")
    @Expose
    private int estado;
    @SerializedName("nombreProducto")
    @Expose
    private String nombreProducto;
    @SerializedName("descripcionProducto")
    @Expose
    private String descripcionProducto;
    @SerializedName("nombreTipo")
    @Expose
    private String nombreTipo;
    @SerializedName("codCortesiaTipo")
    @Expose
    private int codCortesiaTipo;

    /**
     * No args constructor for use in serialization
     *
     */
    public ComboDetalle() {
    }

    /**
     *
     * @param nombreTipo
     * @param descripcionProducto
     * @param estado
     * @param cantidad
     * @param codCortesiaProductos
     * @param fechaRegistro
     * @param codCortesiaCombo
     * @param codCortesiaComboDetalle
     * @param nombreProducto
     * @param codCortesiaTipo
     */
    public ComboDetalle(int codCortesiaComboDetalle, int codCortesiaCombo, int codCortesiaProductos, int cantidad, String fechaRegistro, int estado, String nombreProducto, String descripcionProducto, String nombreTipo, int codCortesiaTipo) {
        super();
        this.codCortesiaComboDetalle = codCortesiaComboDetalle;
        this.codCortesiaCombo = codCortesiaCombo;
        this.codCortesiaProductos = codCortesiaProductos;
        this.cantidad = cantidad;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.nombreTipo = nombreTipo;
        this.codCortesiaTipo = codCortesiaTipo;
    }

    public int getCodCortesiaComboDetalle() {
        return codCortesiaComboDetalle;
    }

    public void setCodCortesiaComboDetalle(int codCortesiaComboDetalle) {
        this.codCortesiaComboDetalle = codCortesiaComboDetalle;
    }

    public int getCodCortesiaCombo() {
        return codCortesiaCombo;
    }

    public void setCodCortesiaCombo(int codCortesiaCombo) {
        this.codCortesiaCombo = codCortesiaCombo;
    }

    public int getCodCortesiaProductos() {
        return codCortesiaProductos;
    }

    public void setCodCortesiaProductos(int codCortesiaProductos) {
        this.codCortesiaProductos = codCortesiaProductos;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public int getCodCortesiaTipo() {
        return codCortesiaTipo;
    }

    public void setCodCortesiaTipo(int codCortesiaTipo) {
        this.codCortesiaTipo = codCortesiaTipo;
    }

}
