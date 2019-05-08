package com.example.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Login{

    @SerializedName("respuesta")
    @Expose
    private boolean respuesta;
    @SerializedName("CambioContrasena")
    @Expose
    private boolean cambioContrasena;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("OlvidoContrasena")
    @Expose
    private boolean olvidoContrasena;
    @SerializedName("usuario")
    @Expose
    private Usuario usuario;
    @SerializedName("rolUsuario")
    @Expose
    private RolUsuario rolUsuario;
    @SerializedName("empleado")
    @Expose
    private Empleado empleado;
    @SerializedName("sala")
    @Expose
    private Sala sala;
    @SerializedName("empresa")
    @Expose
    private Empresa empresa;

    /**
     * No args constructor for use in serialization
     *
     */
    public Login() {
    }

    /**
     *
     * @param cambioContrasena
     * @param empleado
     * @param usuario
     * @param empresa
     * @param olvidoContrasena
     * @param rolUsuario
     * @param mensaje
     * @param sala
     * @param respuesta
     */
    public Login(boolean respuesta, boolean cambioContrasena, String mensaje, boolean olvidoContrasena, Usuario usuario, RolUsuario rolUsuario, Empleado empleado, Sala sala, Empresa empresa) {
        super();
        this.respuesta = respuesta;
        this.cambioContrasena = cambioContrasena;
        this.mensaje = mensaje;
        this.olvidoContrasena = olvidoContrasena;
        this.usuario = usuario;
        this.rolUsuario = rolUsuario;
        this.empleado = empleado;
        this.sala = sala;
        this.empresa = empresa;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isCambioContrasena() {
        return cambioContrasena;
    }

    public void setCambioContrasena(boolean cambioContrasena) {
        this.cambioContrasena = cambioContrasena;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isOlvidoContrasena() {
        return olvidoContrasena;
    }

    public void setOlvidoContrasena(boolean olvidoContrasena) {
        this.olvidoContrasena = olvidoContrasena;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}









