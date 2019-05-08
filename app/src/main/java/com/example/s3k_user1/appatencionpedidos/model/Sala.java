package com.example.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sala {

    @SerializedName("Cod_Sala")
    @Expose
    private String codSala;
    @SerializedName("Nom_Sala")
    @Expose
    private String nomSala;

    /**
     * No args constructor for use in serialization
     *
     */
    public Sala() {
    }

    /**
     *
     * @param codSala
     * @param nomSala
     */
    public Sala(String codSala, String nomSala) {
        super();
        this.codSala = codSala;
        this.nomSala = nomSala;
    }

    public String getCodSala() {
        return codSala;
    }

    public void setCodSala(String codSala) {
        this.codSala = codSala;
    }

    public String getNomSala() {
        return nomSala;
    }

    public void setNomSala(String nomSala) {
        this.nomSala = nomSala;
    }

}