package com.example.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CortesiaProductos {
    @SerializedName("codCortesiaProductos")
    @Expose
    private int codCortesiaProductos;
    @SerializedName("codCortesiaTipo")
    @Expose
    private int codCortesiaTipo;
    @SerializedName("codCortesiaSubTipo")
    @Expose
    private int codCortesiaSubTipo;
    @SerializedName("UsuarioID")
    @Expose
    private int usuarioID;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("NombreTipo")
    @Expose
    private String nombreTipo;
    @SerializedName("NombreSubTipo")
    @Expose
    private String nombreSubTipo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("fechaRegistro")
    @Expose
    private String fechaRegistro;
    @SerializedName("estado")
    @Expose
    private int estado;

    /**
     * No args constructor for use in serialization
     *
     */
    public CortesiaProductos() {
    }

    /**
     *
     * @param nombreTipo
     * @param nombre
     * @param estado
     * @param descripcion
     * @param codCortesiaProductos
     * @param fechaRegistro
     * @param codCortesiaSubTipo
     * @param nombreSubTipo
     * @param usuarioID
     * @param codCortesiaTipo
     */
    public CortesiaProductos(int codCortesiaProductos, int codCortesiaTipo, int codCortesiaSubTipo, int usuarioID, String nombre, String nombreTipo, String nombreSubTipo, String descripcion, String fechaRegistro, int estado) {
        super();
        this.codCortesiaProductos = codCortesiaProductos;
        this.codCortesiaTipo = codCortesiaTipo;
        this.codCortesiaSubTipo = codCortesiaSubTipo;
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.nombreTipo = nombreTipo;
        this.nombreSubTipo = nombreSubTipo;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    public int getCodCortesiaProductos() {
        return codCortesiaProductos;
    }

    public void setCodCortesiaProductos(int codCortesiaProductos) {
        this.codCortesiaProductos = codCortesiaProductos;
    }

    public int getCodCortesiaTipo() {
        return codCortesiaTipo;
    }

    public void setCodCortesiaTipo(int codCortesiaTipo) {
        this.codCortesiaTipo = codCortesiaTipo;
    }

    public int getCodCortesiaSubTipo() {
        return codCortesiaSubTipo;
    }

    public void setCodCortesiaSubTipo(int codCortesiaSubTipo) {
        this.codCortesiaSubTipo = codCortesiaSubTipo;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getNombreSubTipo() {
        return nombreSubTipo;
    }

    public void setNombreSubTipo(String nombreSubTipo) {
        this.nombreSubTipo = nombreSubTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
}
