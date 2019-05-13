package com.software3000.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaquinaZona {

    @SerializedName("codMaqZona")
    @Expose
    private String codMaqZona;
    @SerializedName("CodMaq")
    @Expose
    private String codMaq;
    @SerializedName("CodZona")
    @Expose
    private String codZona;
    @SerializedName("CodIsla")
    @Expose
    private String codIsla;
    @SerializedName("posicion")
    @Expose
    private String posicion;

    /**
     * No args constructor for use in serialization
     *
     */
    public MaquinaZona() {
    }

    /**
     *
     * @param codZona
     * @param codMaq
     * @param codMaqZona
     * @param codIsla
     * @param posicion
     */
    public MaquinaZona(String codMaqZona, String codMaq, String codZona, String codIsla, String posicion) {
        super();
        this.codMaqZona = codMaqZona;
        this.codMaq = codMaq;
        this.codZona = codZona;
        this.codIsla = codIsla;
        this.posicion = posicion;
    }

    public String getCodMaqZona() {
        return codMaqZona;
    }

    public void setCodMaqZona(String codMaqZona) {
        this.codMaqZona = codMaqZona;
    }

    public String getCodMaq() {
        return codMaq;
    }

    public void setCodMaq(String codMaq) {
        this.codMaq = codMaq;
    }

    public String getCodZona() {
        return codZona;
    }

    public void setCodZona(String codZona) {
        this.codZona = codZona;
    }

    public String getCodIsla() {
        return codIsla;
    }

    public void setCodIsla(String codIsla) {
        this.codIsla = codIsla;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

}