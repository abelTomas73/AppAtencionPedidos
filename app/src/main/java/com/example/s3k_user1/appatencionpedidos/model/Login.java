package com.example.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




public class Login {

    @SerializedName("usuarioId")
    @Expose
    private String usuarioId;
    @SerializedName("usuarioNombre")
    @Expose
    private String usuarioNombre;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("cargo")
    @Expose
    private String cargo;
    @SerializedName("rol")
    @Expose
    private String rol;
    @SerializedName("respuesta")
    @Expose
    private Boolean respuesta;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("empleado")
    @Expose
    private Empleado empleado;
    @SerializedName("usuario")
    @Expose
    private Usuario usuario;

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Boolean respuesta) {
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public class Empleado {

        @SerializedName("NombreCompleto")
        @Expose
        private Object nombreCompleto;
        @SerializedName("EmpleadoID")
        @Expose
        private Integer empleadoID;
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
        private Integer cargoID;
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
        private Integer dOIID;
        @SerializedName("DOIIDNombre")
        @Expose
        private String dOIIDNombre;
        @SerializedName("DOI")
        @Expose
        private String dOI;
        @SerializedName("Telefono")
        @Expose
        private String telefono;
        @SerializedName("Movil")
        @Expose
        private Object movil;
        @SerializedName("Genero")
        @Expose
        private String genero;
        @SerializedName("MailJob")
        @Expose
        private String mailJob;
        @SerializedName("MailPersonal")
        @Expose
        private String mailPersonal;
        @SerializedName("EstadoEmpleado")
        @Expose
        private Integer estadoEmpleado;
        @SerializedName("FechaAlta")
        @Expose
        private String fechaAlta;
        @SerializedName("Perfil")
        @Expose
        private Integer perfil;
        @SerializedName("rol_legal")
        @Expose
        private Integer rolLegal;

        public Object getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(Object nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public Integer getEmpleadoID() {
            return empleadoID;
        }

        public void setEmpleadoID(Integer empleadoID) {
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

        public Integer getCargoID() {
            return cargoID;
        }

        public void setCargoID(Integer cargoID) {
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

        public Integer getDOIID() {
            return dOIID;
        }

        public void setDOIID(Integer dOIID) {
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

        public Object getMovil() {
            return movil;
        }

        public void setMovil(Object movil) {
            this.movil = movil;
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

        public String getMailPersonal() {
            return mailPersonal;
        }

        public void setMailPersonal(String mailPersonal) {
            this.mailPersonal = mailPersonal;
        }

        public Integer getEstadoEmpleado() {
            return estadoEmpleado;
        }

        public void setEstadoEmpleado(Integer estadoEmpleado) {
            this.estadoEmpleado = estadoEmpleado;
        }

        public String getFechaAlta() {
            return fechaAlta;
        }

        public void setFechaAlta(String fechaAlta) {
            this.fechaAlta = fechaAlta;
        }

        public Integer getPerfil() {
            return perfil;
        }

        public void setPerfil(Integer perfil) {
            this.perfil = perfil;
        }

        public Integer getRolLegal() {
            return rolLegal;
        }

        public void setRolLegal(Integer rolLegal) {
            this.rolLegal = rolLegal;
        }

    }

    public class Usuario {

        @SerializedName("NombreEmpleado")
        @Expose
        private String nombreEmpleado;
        @SerializedName("MailJob")
        @Expose
        private Object mailJob;
        @SerializedName("UsuarioID")
        @Expose
        private Integer usuarioID;
        @SerializedName("EmpleadoID")
        @Expose
        private Integer empleadoID;
        @SerializedName("TipoUsuarioID")
        @Expose
        private Integer tipoUsuarioID;
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
        private Integer failedAttempts;
        @SerializedName("Estado")
        @Expose
        private Integer estado;
        @SerializedName("EstadoContrasena")
        @Expose
        private Integer estadoContrasena;
        @SerializedName("SesionActiva")
        @Expose
        private Boolean sesionActiva;

        public String getNombreEmpleado() {
            return nombreEmpleado;
        }

        public void setNombreEmpleado(String nombreEmpleado) {
            this.nombreEmpleado = nombreEmpleado;
        }

        public Object getMailJob() {
            return mailJob;
        }

        public void setMailJob(Object mailJob) {
            this.mailJob = mailJob;
        }

        public Integer getUsuarioID() {
            return usuarioID;
        }

        public void setUsuarioID(Integer usuarioID) {
            this.usuarioID = usuarioID;
        }

        public Integer getEmpleadoID() {
            return empleadoID;
        }

        public void setEmpleadoID(Integer empleadoID) {
            this.empleadoID = empleadoID;
        }

        public Integer getTipoUsuarioID() {
            return tipoUsuarioID;
        }

        public void setTipoUsuarioID(Integer tipoUsuarioID) {
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

        public Integer getFailedAttempts() {
            return failedAttempts;
        }

        public void setFailedAttempts(Integer failedAttempts) {
            this.failedAttempts = failedAttempts;
        }

        public Integer getEstado() {
            return estado;
        }

        public void setEstado(Integer estado) {
            this.estado = estado;
        }

        public Integer getEstadoContrasena() {
            return estadoContrasena;
        }

        public void setEstadoContrasena(Integer estadoContrasena) {
            this.estadoContrasena = estadoContrasena;
        }

        public Boolean getSesionActiva() {
            return sesionActiva;
        }

        public void setSesionActiva(Boolean sesionActiva) {
            this.sesionActiva = sesionActiva;
        }

    }
}


