package com.example.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RolUsuario {

    @SerializedName("WEB_RUsuID")
    @Expose
    private int wEBRUsuID;
    @SerializedName("WEB_RolID")
    @Expose
    private int wEBRolID;
    @SerializedName("UsuarioID")
    @Expose
    private int usuarioID;
    @SerializedName("WEB_RUsuFechaRegistro")
    @Expose
    private String wEBRUsuFechaRegistro;

    /**
     * No args constructor for use in serialization
     *
     */
    public RolUsuario() {
    }

    /**
     *
     * @param wEBRolID
     * @param wEBRUsuFechaRegistro
     * @param wEBRUsuID
     * @param usuarioID
     */
    public RolUsuario(int wEBRUsuID, int wEBRolID, int usuarioID, String wEBRUsuFechaRegistro) {
        super();
        this.wEBRUsuID = wEBRUsuID;
        this.wEBRolID = wEBRolID;
        this.usuarioID = usuarioID;
        this.wEBRUsuFechaRegistro = wEBRUsuFechaRegistro;
    }

    public int getWEBRUsuID() {
        return wEBRUsuID;
    }

    public void setWEBRUsuID(int wEBRUsuID) {
        this.wEBRUsuID = wEBRUsuID;
    }

    public int getWEBRolID() {
        return wEBRolID;
    }

    public void setWEBRolID(int wEBRolID) {
        this.wEBRolID = wEBRolID;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getWEBRUsuFechaRegistro() {
        return wEBRUsuFechaRegistro;
    }

    public void setWEBRUsuFechaRegistro(String wEBRUsuFechaRegistro) {
        this.wEBRUsuFechaRegistro = wEBRUsuFechaRegistro;
    }

}