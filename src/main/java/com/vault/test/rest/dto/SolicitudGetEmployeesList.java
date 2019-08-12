package com.vault.test.rest.dto;

public class SolicitudGetEmployeesList {

    private Long jobId;

    private Long managerId;

    private String lastName;

    /* parametros para paginacion de los resultados */
    private int cantidadRegistros = 0;
    private int registroInicial = 0;

    public Long getJobId() {
        return jobId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public int getRegistroInicial() {
        return registroInicial;
    }
}
