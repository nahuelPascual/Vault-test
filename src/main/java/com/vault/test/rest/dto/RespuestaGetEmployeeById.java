package com.vault.test.rest.dto;

import com.vault.test.rest.RespuestaRestService;

public class RespuestaGetEmployeeById extends RespuestaRestService {

    EmployeeDto employeeDto ;

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }
}
