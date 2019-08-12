package com.vault.test.rest;

import java.util.ArrayList;
import java.util.List;

public class RespuestaRestService {

    private List<String> errores = new ArrayList<>();

    private RespuestaServicio respuestaServicio = RespuestaServicio.OK;

    public enum RespuestaServicio{
        OK(0),
        ERROR(1),
        VALIDATION_ERROR(2);

        private int i;

        RespuestaServicio(int i){
            this.i = 0;
        }

        public int getValue(){
        return i;
    }
    }

    public List<String> getErrores() {
        return errores;
    }

    public void addError(String error) {
        this.errores.add(error);
    }

    public RespuestaServicio getRespuestaServicio() {
        return respuestaServicio;
    }

    public void setRespuestaServicio(RespuestaServicio respuestaServicio) {
        this.respuestaServicio = respuestaServicio;
    }
}
