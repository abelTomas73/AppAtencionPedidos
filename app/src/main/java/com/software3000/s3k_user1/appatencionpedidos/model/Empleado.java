package com.software3000.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Empleado {

    @SerializedName("NombreCompleto")
    @Expose
    private Object nombreCompleto;
    @SerializedName("EmpleadoID")
    @Expose
    private int empleadoID;
    @SerializedName("Nombres")
    @Expose
    private String nombres;
    @SerializedName("ApellidosPaterno")
    @Expose
    private String apellidosPaterno;
    @SerializedName("ApellidosMaterno")
    @Expose
    private String apellidosMaterno;
    @SerializedName("CargoID")
    @Expose
    private int cargoID;
    @SerializedName("CargoNombre")
    @Expose
    private String cargoNombre;
    @SerializedName("FechaNacimiento")
    @Expose
    private String fechaNacimiento;
    @SerializedName("Direccion")
    @Expose
    private String direccion;
    @SerializedName("DOIID")
    @Expose
    private int dOIID;
    @SerializedName("DOIIDNombre")
    @Expose
    private String dOIIDNombre;
    @SerializedName("DOI")
    @Expose
    private String dOI;
    @SerializedName("Telefono")
    @Expose
    private String telefono;
    @SerializedName("Genero")
    @Expose
    private String genero;
    @SerializedName("MailJob")
    @Expose
    private String mailJob;
    @SerializedName("EstadoEmpleado")
    @Expose
    private int estadoEmpleado;
    @SerializedName("FechaAlta")
    @Expose
    private String fechaAlta;

    /**
     * No args constructor for use in serialization
     *
     */
    public Empleado() {
    }

    /**
     *
     * @param direccion
     * @param empleadoID
     * @param cargoNombre
     * @param estadoEmpleado
     * @param cargoID
     * @param telefono
     * @param nombreCompleto
     * @param fechaNacimiento
     * @param dOIIDNombre
     * @param apellidosMaterno
     * @param genero
     * @param nombres
     * @param dOI
     * @param mailJob
     * @param fechaAlta
     * @param apellidosPaterno
     * @param dOIID
     */
    public Empleado(Object nombreCompleto, int empleadoID, String nombres, String apellidosPaterno, String apellidosMaterno, int cargoID, String cargoNombre, String fechaNacimiento, String direccion, int dOIID, String dOIIDNombre, String dOI, String telefono, String genero, String mailJob, int estadoEmpleado, String fechaAlta) {
        super();
        this.nombreCompleto = nombreCompleto;
        this.empleadoID = empleadoID;
        this.nombres = nombres;
        this.apellidosPaterno = apellidosPaterno;
        this.apellidosMaterno = apellidosMaterno;
        this.cargoID = cargoID;
        this.cargoNombre = cargoNombre;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.dOIID = dOIID;
        this.dOIIDNombre = dOIIDNombre;
        this.dOI = dOI;
        this.telefono = telefono;
        this.genero = genero;
        this.mailJob = mailJob;
        this.estadoEmpleado = estadoEmpleado;
        this.fechaAlta = fechaAlta;
    }

    public Object getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(Object nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(int empleadoID) {
        this.empleadoID = empleadoID;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidosPaterno() {
        return apellidosPaterno;
    }

    public void setApellidosPaterno(String apellidosPaterno) {
        this.apellidosPaterno = apellidosPaterno;
    }

    public String getApellidosMaterno() {
        return apellidosMaterno;
    }

    public void setApellidosMaterno(String apellidosMaterno) {
        this.apellidosMaterno = apellidosMaterno;
    }

    public int getCargoID() {
        return cargoID;
    }

    public void setCargoID(int cargoID) {
        this.cargoID = cargoID;
    }

    public String getCargoNombre() {
        return cargoNombre;
    }

    public void setCargoNombre(String cargoNombre) {
        this.cargoNombre = cargoNombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getDOIID() {
        return dOIID;
    }

    public void setDOIID(int dOIID) {
        this.dOIID = dOIID;
    }

    public String getDOIIDNombre() {
        return dOIIDNombre;
    }

    public void setDOIIDNombre(String dOIIDNombre) {
        this.dOIIDNombre = dOIIDNombre;
    }

    public String getDOI() {
        return dOI;
    }

    public void setDOI(String dOI) {
        this.dOI = dOI;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMailJob() {
        return mailJob;
    }

    public void setMailJob(String mailJob) {
        this.mailJob = mailJob;
    }

    public int getEstadoEmpleado() {
        return estadoEmpleado;
    }

    public void setEstadoEmpleado(int estadoEmpleado) {
        this.estadoEmpleado = estadoEmpleado;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

}
