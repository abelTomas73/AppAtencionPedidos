package com.software3000.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CortesiaCombo {

    @SerializedName("CodCortesiaCombo")
    @Expose
    private Integer codCortesiaCombo;
    @SerializedName("UsuarioID")
    @Expose
    private Integer usuarioID;
    @SerializedName("cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("codProductos")
    @Expose
    private String codProductos;
    @SerializedName("fechaRegistro")
    @Expose
    private String fechaRegistro;
    @SerializedName("estado")
    @Expose
    private Integer estado;
    @SerializedName("estadoTurnoValido")
    @Expose
    private Integer estadoTurnoValido;

    /**
     * No args constructor for use in serialization
     *
     */
    public CortesiaCombo() {
    }

    /**
     *
     * @param nombre
     * @param estado
     * @param codProductos
     * @param cantidad
     * @param descripcion
     * @param fechaRegistro
     * @param codCortesiaCombo
     * @param usuarioID
     */
    public CortesiaCombo(Integer codCortesiaCombo, Integer usuarioID, Integer cantidad, String descripcion, String nombre, String codProductos, String fechaRegistro, Integer estado) {
        super();
        this.codCortesiaCombo = codCortesiaCombo;
        this.usuarioID = usuarioID;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.codProductos = codProductos;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    public Integer getEstadoTurnoValido() {
        return estadoTurnoValido;
    }

    public void setEstadoTurnoValido(Integer estadoTurnoValido) {
        this.estadoTurnoValido = estadoTurnoValido;
    }

    public Integer getCodCortesiaCombo() {
        return codCortesiaCombo;
    }

    public void setCodCortesiaCombo(Integer codCortesiaCombo) {
        this.codCortesiaCombo = codCortesiaCombo;
    }

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodProductos() {
        return codProductos;
    }

    public void setCodProductos(String codProductos) {
        this.codProductos = codProductos;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

}