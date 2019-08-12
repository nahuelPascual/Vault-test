package com.vault.test.model.dao;

import com.vault.test.model.entity.Employee;
import com.vault.test.model.exception.DaoException;
import com.vault.test.model.exception.ValidationException;
import com.vault.test.rest.dto.SolicitudGetEmployeesList;

import java.util.List;

public interface EmployeeDao extends EntityDao<Long, Employee> {

    Employee getEmployee (Long id) throws DaoException, ValidationException;

    List<Employee> getEmployeeList (SolicitudGetEmployeesList solicitudGetEmployeesList) throws DaoException;

    Double getAvgSalaryLocation(Long locationId) throws DaoException;

}
