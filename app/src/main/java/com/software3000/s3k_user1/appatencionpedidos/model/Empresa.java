package com.software3000.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Empresa {

    @SerializedName("CodEmpresa")
    @Expose
    private String codEmpresa;
    @SerializedName("RazonSocial")
    @Expose
    private String razonSocial;
    @SerializedName("codConsorcio")
    @Expose
    private String codConsorcio;

    /**
     * No args constructor for use in serialization
     *
     */
    public Empresa() {
    }

    /**
     *
     * @param codConsorcio
     * @param razonSocial
     * @param codEmpresa
     */
    public Empresa(String codEmpresa, String razonSocial, String codConsorcio) {
        super();
        this.codEmpresa = codEmpresa;
        this.razonSocial = razonSocial;
        this.codConsorcio = codConsorcio;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCodConsorcio() {
        return codConsorcio;
    }

    public void setCodConsorcio(String codConsorcio) {
        this.codConsorcio = codConsorcio;
    }

}