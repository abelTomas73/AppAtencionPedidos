package com.software3000.s3k_user1.appatencionpedidos.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CortesiaAtencion {

    @SerializedName("codCortesiaAtencion")
    @Expose
    private String codCortesiaAtencion;

    @SerializedName("CodMaq")
    @Expose
    private String codMaq;

    @SerializedName("Codzona")
    @Expose
    private String Codzona;

    @SerializedName("codisla")
    @Expose
    private String codisla;


    @SerializedName("UsuarioRegistroID")
    @Expose
    private String usuarioRegistroID;
    @SerializedName("UsuarioAtencionID")
    @Expose
    private String usuarioAtencionID;
    @SerializedName("codCortesiaCombo")
    @Expose
    private String codCortesiaCombo;
    @SerializedName("codTurno")
    @Expose
    private String codTurno;
    @SerializedName("codSala")
    @Expose
    private String codSala;
    @SerializedName("codEmpresa")
    @Expose
    private String codEmpresa;
    @SerializedName("Comentario")
    @Expose
    private String comentario;
    @SerializedName("estado")
    @Expose
    private String estado;
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
    public CortesiaAtencion() {
    }

    /**
     *
     * @param usuarioAtencionID
     * @param codSala
     * @param codMaq
     * @param fechaRegistro
     * @param usuarioRegistroID
     * @param fechaAtencion
     * @param fechaTermino
     * @param comentario
     * @param estado
     * @param codCortesiaCombo
     * @param codTurno
     * @param codCortesiaAtencion
     * @param codEmpresa
     */
    public CortesiaAtencion(String codCortesiaAtencion, String codMaq, String usuarioRegistroID, String usuarioAtencionID, String codCortesiaCombo, String codTurno, String codSala, String codEmpresa, String comentario, String estado, String fechaRegistro, String fechaAtencion, String fechaTermino) {
        super();
        this.codCortesiaAtencion = codCortesiaAtencion;
        this.codMaq = codMaq;
        this.usuarioRegistroID = usuarioRegistroID;
        this.usuarioAtencionID = usuarioAtencionID;
        this.codCortesiaCombo = codCortesiaCombo;
        this.codTurno = codTurno;
        this.codSala = codSala;
        this.codEmpresa = codEmpresa;
        this.comentario = comentario;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaAtencion = fechaAtencion;
        this.fechaTermino = fechaTermino;
    }

    public String getCodzona() {
        return Codzona;
    }

    public void setCodzona(String codzona) {
        Codzona = codzona;
    }

    public String getCodisla() {
        return codisla;
    }

    public void setCodisla(String codisla) {
        this.codisla = codisla;
    }

    public String getCodCortesiaAtencion() {
        return codCortesiaAtencion;
    }

    public void setCodCortesiaAtencion(String codCortesiaAtencion) {
        this.codCortesiaAtencion = codCortesiaAtencion;
    }

    public String getCodMaq() {
        return codMaq;
    }

    public void setCodMaq(String codMaq) {
        this.codMaq = codMaq;
    }

    public String getUsuarioRegistroID() {
        return usuarioRegistroID;
    }

    public void setUsuarioRegistroID(String usuarioRegistroID) {
        this.usuarioRegistroID = usuarioRegistroID;
    }

    public String getUsuarioAtencionID() {
        return usuarioAtencionID;
    }

    public void setUsuarioAtencionID(String usuarioAtencionID) {
        this.usuarioAtencionID = usuarioAtencionID;
    }

    public String getCodCortesiaCombo() {
        return codCortesiaCombo;
    }

    public void setCodCortesiaCombo(String codCortesiaCombo) {
        this.codCortesiaCombo = codCortesiaCombo;
    }

    public String getCodTurno() {
        return codTurno;
    }

    public void setCodTurno(String codTurno) {
        this.codTurno = codTurno;
    }

    public String getCodSala() {
        return codSala;
    }

    public void setCodSala(String codSala) {
        this.codSala = codSala;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(String fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

}