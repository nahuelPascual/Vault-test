package com.vault.test.mapper.impl;

import com.vault.test.mapper.DepartmentMapper;
import com.vault.test.model.entity.Department;
import com.vault.test.model.exception.DaoException;
import com.vault.test.rest.dto.DepartmentDto;

import java.text.ParseException;
import java.util.List;

public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public void map(DepartmentDto employeeDto, DepartmentDto employee) throws DaoException {

    }

    @Override
    public void map(Department employee, DepartmentDto employeeDto) throws ParseException {

    }

    @Override
    public void map(List<Department> employees, List<DepartmentDto> employeeDtos) {

    }
}
