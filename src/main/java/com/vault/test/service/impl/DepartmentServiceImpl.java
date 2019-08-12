package com.vault.test.service.impl;

import com.vault.test.model.dao.DepartmentDao;
import com.vault.test.model.dao.EmployeeDao;
import com.vault.test.model.dao.LocationDao;
import com.vault.test.model.entity.Department;
import com.vault.test.model.exception.DaoException;
import com.vault.test.model.exception.ValidationException;
import com.vault.test.model.utils.Messages;
import com.vault.test.rest.dto.DepartmentDto;
import com.vault.test.service.DepartmentService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Named
@Transactional(rollbackOn = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {

    @Inject
    private EmployeeDao employeeDao;

    @Inject
    private LocationDao locationDao;

    @Inject
    private DepartmentDao departmentDao;

    @Inject
    private Messages messages;

    @Override
    public void create(DepartmentDto departmentDto) throws DaoException, ValidationException {
        validate(departmentDto);

        Department department = new Department();
        department.setDepartmentId(departmentDto.getDepartmentId());
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setLocation(locationDao.getLocation(departmentDto.getLocationId()));
        department.setManager(employeeDao.getEmployee(departmentDto.getManagerId()));

        departmentDao.persist(department);
    }

    private void validate(DepartmentDto departmentDto) throws DaoException, ValidationException{
        Double salarioPromedio = employeeDao.getAvgSalaryLocation(departmentDto.getLocationId());

        if((LocalDate.now().getDayOfMonth() < 15 && salarioPromedio > 1000)
                || (!(LocalDate.now().getDayOfMonth() < 15) && salarioPromedio > 1500)){
            throw new ValidationException(messages.get("error.validation.createDept", salarioPromedio.toString()));
        }
    }
}
