package com.vault.test.service;

import com.vault.test.rest.dto.EmployeeDto;
import com.vault.test.model.exception.DaoException;
import com.vault.test.model.exception.ValidationException;
import com.vault.test.rest.dto.SolicitudGetEmployeesList;

import java.util.List;

public interface EmployeeService {

    void createOrUpdate(EmployeeDto employeeDto) throws DaoException, ValidationException;

    void delete(Long employeeId) throws DaoException, Exception;

    EmployeeDto getById (Long employeeId) throws Exception;

    List<EmployeeDto> getEmployees(SolicitudGetEmployeesList solicitudGetEmployeesList) throws DaoException, Exception;

    }
