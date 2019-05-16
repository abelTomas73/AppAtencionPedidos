package com.software3000.s3k_user1.appatencionpedidos.model;

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

    @SerializedName("estadoTurnoValido")
    @Expose
    private int estadoTurnoValido;


    @SerializedName("nombreArchivo")
    @Expose
    private String nombreArchivo;
    @SerializedName("Archivo64String")
    @Expose
    private String Archivo64String;
    /**
     * No args constructor for use in serialization
     *
     */
    public CortesiaProductos() {
    }

    public int getEstadoTurnoValido() {
        return estadoTurnoValido;
    }

    public void setEstadoTurnoValido(int estadoTurnoValido) {
        this.estadoTurnoValido = estadoTurnoValido;
    }

    public CortesiaProductos(int codCortesiaProductos, int codCortesiaTipo, int codCortesiaSubTipo, int usuarioID, String nombre, String nombreTipo, String nombreSubTipo, String descripcion, String fechaRegistro, int estado, String nombreArchivo, String archivo64String) {
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
        this.nombreArchivo = nombreArchivo;
        Archivo64String = archivo64String;
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

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getArchivo64String() {
        return Archivo64String;
    }

    public void setArchivo64String(String archivo64String) {
        Archivo64String = archivo64String;
    }
}
