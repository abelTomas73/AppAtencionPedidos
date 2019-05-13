package com.software3000.s3k_user1.appatencionpedidos.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CortesiaPedido {

    @SerializedName("codCortesiaPedido")
    @Expose
    private int codCortesiaPedido;
    @SerializedName("CodMaq")
    @Expose
    private String codMaq;
    @SerializedName("codSala")
    @Expose
    private int codSala;
    @SerializedName("codEmpresa")
    @Expose
    private int codEmpresa;
    @SerializedName("UsuarioRegistroID")
    @Expose
    private int usuarioRegistroID;
    @SerializedName("UsuarioAtencionID")
    @Expose
    private int usuarioAtencionID;
    @SerializedName("codTurno")
    @Expose
    private int codTurno;
    @SerializedName("Comentario")
    @Expose
    private Object comentario;
    @SerializedName("estado")
    @Expose
    private int estado;
    @SerializedName("fechaRegistro")
    @Expose
    private String fechaRegistro;
    @SerializedName("FechaAtencion")
    @Expose
    private String fechaAtencion;
    @SerializedName("FechaTermino")
    @Expose
    private String fechaTermino;

    /**
     * No args constructor for use in serialization
     *
     */
    public CortesiaPedido() {
    }

    /**
     *
     * @param usuarioAtencionID
     * @param codSala
     * @param estado
     * @param codMaq
     * @param fechaRegistro
     * @param codTurno
     * @param usuarioRegistroID
     * @param codCortesiaPedido
     * @param fechaAtencion
     * @param fechaTermino
     * @param codEmpresa
     * @param comentario
     */
    public CortesiaPedido(int codCortesiaPedido, String codMaq, int codSala, int codEmpresa, int usuarioRegistroID, int usuarioAtencionID, int codTurno, Object comentario, int estado, String fechaRegistro, String fechaAtencion, String fechaTermino) {
        super();
        this.codCortesiaPedido = codCortesiaPedido;
        this.codMaq = codMaq;
        this.codSala = codSala;
        this.codEmpresa = codEmpresa;
        this.usuarioRegistroID = usuarioRegistroID;
        this.usuarioAtencionID = usuarioAtencionID;
        this.codTurno = codTurno;
        this.comentario = comentario;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaAtencion = fechaAtencion;
        this.fechaTermino = fechaTermino;
    }

    public int getCodCortesiaPedido() {
        return codCortesiaPedido;
    }

    public void setCodCortesiaPedido(int codCortesiaPedido) {
        this.codCortesiaPedido = codCortesiaPedido;
    }

    public String getCodMaq() {
        return codMaq;
    }

    public void setCodMaq(String codMaq) {
        this.codMaq = codMaq;
    }

    public int getCodSala() {
        return codSala;
    }

    public void setCodSala(int codSala) {
        this.codSala = codSala;
    }

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public int getUsuarioRegistroID() {
        return usuarioRegistroID;
    }

    public void setUsuarioRegistroID(int usuarioRegistroID) {
        this.usuarioRegistroID = usuarioRegistroID;
    }

    public int getUsuarioAtencionID() {
        return usuarioAtencionID;
    }

    public void setUsuarioAtencionID(int usuarioAtencionID) {
        this.usuarioAtencionID = usuarioAtencionID;
    }

    public int getCodTurno() {
        return codTurno;
    }

    public void setCodTurno(int codTurno) {
        this.codTurno = codTurno;
    }

    public Object getComentario() {
        return comentario;
    }

    public void setComentario(Object comentario) {
        this.comentario = comentario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Object getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public Object getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

}