package com.vault.test.mapper;

import com.vault.test.rest.dto.EmployeeDto;
import com.vault.test.model.entity.Employee;
import com.vault.test.model.exception.DaoException;

import java.text.ParseException;
import java.util.List;

public interface EmployeeMapper {

    void map(EmployeeDto employeeDto, Employee employee) throws DaoException;

    void map(Employee employee, EmployeeDto employeeDto) throws ParseException;

    void map(List<Employee> employees, List<EmployeeDto> employeeDtos);

    void merge(EmployeeDto employeeDto, Employee employee) throws DaoException;
}
