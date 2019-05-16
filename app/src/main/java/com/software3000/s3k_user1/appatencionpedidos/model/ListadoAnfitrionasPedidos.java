package com.software3000.s3k_user1.appatencionpedidos.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListadoAnfitrionasPedidos {

    @SerializedName("codPedido")
    @Expose
    private int codPedido;
    @SerializedName("codMaq")
    @Expose
    private String codMaq;
    @SerializedName("usuarioID")
    @Expose
    private int usuarioID;
    @SerializedName("listaPedido")
    @Expose
    private List<ListaPedido> listaPedido = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ListadoAnfitrionasPedidos() {
    }

    /**
     *
     * @param codPedido
     * @param codMaq
     * @param listaPedido
     * @param usuarioID
     */
    public ListadoAnfitrionasPedidos(int codPedido, String codMaq, int usuarioID, List<ListaPedido> listaPedido) {
        super();
        this.codPedido = codPedido;
        this.codMaq = codMaq;
        this.usuarioID = usuarioID;
        this.listaPedido = listaPedido;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public String getCodMaq() {
        return codMaq;
    }

    public void setCodMaq(String codMaq) {
        this.codMaq = codMaq;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public List<ListaPedido> getListaPedido() {
        return listaPedido;
    }

    public void setListaPedido(List<ListaPedido> listaPedido) {
        this.listaPedido = listaPedido;
    }



}