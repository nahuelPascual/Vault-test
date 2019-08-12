package com.vault.test.rest.dto;

import com.vault.test.rest.RespuestaRestService;

import java.util.ArrayList;
import java.util.List;

public class RespuestaGetEmployees extends RespuestaRestService {

    List<EmployeeDto> respuesta = new ArrayList<>();

    public List<EmployeeDto> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<EmployeeDto> respuesta) {
        this.respuesta = respuesta;
    }
}
