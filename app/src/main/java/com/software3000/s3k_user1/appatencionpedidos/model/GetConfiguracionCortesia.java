package com.software3000.s3k_user1.appatencionpedidos.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetConfiguracionCortesia {

    @SerializedName("codCortesiaConfiguracion")
    @Expose
    private boolean respuesta;

    @SerializedName("tiempoAtencion")
    @Expose
    private CortesiaConfiguracion tiempoAtencion;

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public GetConfiguracionCortesia() {
    }

    public GetConfiguracionCortesia(boolean respuesta, CortesiaConfiguracion tiempoAtencion, String mensaje) {
        this.respuesta = respuesta;
        this.tiempoAtencion = tiempoAtencion;
        this.mensaje = mensaje;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public CortesiaConfiguracion getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(CortesiaConfiguracion tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
