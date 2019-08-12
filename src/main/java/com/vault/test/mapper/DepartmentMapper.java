package com.vault.test.mapper;

import com.vault.test.model.entity.Department;
import com.vault.test.model.exception.DaoException;
import com.vault.test.rest.dto.DepartmentDto;

import java.text.ParseException;
import java.util.List;

public interface DepartmentMapper {

    void map(DepartmentDto employeeDto, DepartmentDto employee) throws DaoException;

    void map(Department employee, DepartmentDto employeeDto) throws ParseException;

    void map(List<Department> employees, List<DepartmentDto> employeeDtos);
}
