package com.software3000.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("NombreEmpleado")
    @Expose
    private Object nombreEmpleado;
    @SerializedName("UsuarioID")
    @Expose
    private int usuarioID;
    @SerializedName("EmpleadoID")
    @Expose
    private int empleadoID;
    @SerializedName("TipoUsuarioID")
    @Expose
    private int tipoUsuarioID;
    @SerializedName("UsuarioNombre")
    @Expose
    private String usuarioNombre;
    @SerializedName("UsuarioContrase\u00f1a")
    @Expose
    private String usuarioContraseA;
    @SerializedName("FechaRegistro")
    @Expose
    private String fechaRegistro;
    @SerializedName("FailedAttempts")
    @Expose
    private int failedAttempts;
    @SerializedName("Estado")
    @Expose
    private int estado;
    @SerializedName("EstadoContrasena")
    @Expose
    private int estadoContrasena;
    @SerializedName("MailJob")
    @Expose
    private Object mailJob;

    /**
     * No args constructor for use in serialization
     *
     */
    public Usuario() {
    }

    /**
     *
     * @param usuarioNombre
     * @param tipoUsuarioID
     * @param usuarioContraseA
     * @param estado
     * @param empleadoID
     * @param failedAttempts
     * @param nombreEmpleado
     * @param mailJob
     * @param estadoContrasena
     * @param fechaRegistro
     * @param usuarioID
     */
    public Usuario(Object nombreEmpleado, int usuarioID, int empleadoID, int tipoUsuarioID, String usuarioNombre, String usuarioContraseA, String fechaRegistro, int failedAttempts, int estado, int estadoContrasena, Object mailJob) {
        super();
        this.nombreEmpleado = nombreEmpleado;
        this.usuarioID = usuarioID;
        this.empleadoID = empleadoID;
        this.tipoUsuarioID = tipoUsuarioID;
        this.usuarioNombre = usuarioNombre;
        this.usuarioContraseA = usuarioContraseA;
        this.fechaRegistro = fechaRegistro;
        this.failedAttempts = failedAttempts;
        this.estado = estado;
        this.estadoContrasena = estadoContrasena;
        this.mailJob = mailJob;
    }

    public Object getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(Object nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public int getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(int empleadoID) {
        this.empleadoID = empleadoID;
    }

    public int getTipoUsuarioID() {
        return tipoUsuarioID;
    }

    public void setTipoUsuarioID(int tipoUsuarioID) {
        this.tipoUsuarioID = tipoUsuarioID;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioContraseA() {
        return usuarioContraseA;
    }

    public void setUsuarioContraseA(String usuarioContraseA) {
        this.usuarioContraseA = usuarioContraseA;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstadoContrasena() {
        return estadoContrasena;
    }

    public void setEstadoContrasena(int estadoContrasena) {
        this.estadoContrasena = estadoContrasena;
    }

    public Object getMailJob() {
        return mailJob;
    }

    public void setMailJob(Object mailJob) {
        this.mailJob = mailJob;
    }

}
