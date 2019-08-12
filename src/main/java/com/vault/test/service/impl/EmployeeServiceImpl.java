package com.vault.test.service.impl;

import com.vault.test.mapper.EmployeeMapper;
import com.vault.test.model.dao.EmployeeDao;
import com.vault.test.model.utils.DateUtility;
import com.vault.test.rest.dto.EmployeeDto;
import com.vault.test.model.entity.Employee;
import com.vault.test.model.exception.DaoException;
import com.vault.test.model.exception.ValidationException;
import com.vault.test.model.utils.Messages;
import com.vault.test.rest.dto.SolicitudGetEmployeesList;
import com.vault.test.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Transactional(rollbackOn = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeDao employeeDao;

    @Inject
    private EmployeeMapper employeeMapper;

    @Inject
    private Messages messages;

    @Override
    public void delete(Long employeeId) throws Exception {
        if(employeeId==null){
            throw new Exception(messages.get("error.validation.idObligatorio"));
        } else if (employeeDao.findById(employeeId) == null){
            throw new Exception(messages.get("error.validation.employeeInexistente"));
        }
        employeeDao.remove(employeeId);
    }

    @Override
    public void createOrUpdate(EmployeeDto employeeDto) throws DaoException, ValidationException {
        Employee employee = employeeDao.findById(employeeDto.getEmployeeId());

        if(employee == null){
            validate(employeeDto);
            employee = new Employee();
            employeeMapper.map(employeeDto, employee);
        } else {
            employeeMapper.merge(employeeDto, employee);
        }

        employeeDao.persist(employee);
    }

    @Override
    public List<EmployeeDto> getEmployees(SolicitudGetEmployeesList solicitudGetEmployeesList) throws DaoException, Exception {
        List<Employee> employees = employeeDao.getEmployeeList(solicitudGetEmployeesList);
        List<EmployeeDto> employeesDto = new ArrayList<>();

        employeeMapper.map(employees.stream()
                .sorted(Comparator.comparingLong(e -> e.getHireDate()
                        .getTime())).collect(Collectors.toList())
                , employeesDto);

        return employeesDto;
    }

    @Override
    public EmployeeDto getById (Long employeeId) throws Exception{
        EmployeeDto employeeDto = new EmployeeDto();
        employeeMapper.map((Employee) employeeDao.getEmployee(employeeId), employeeDto);

        return employeeDto;
    }

    private void validate(EmployeeDto employeeDto) throws ValidationException {
        String error = "" ;
        if(StringUtils.isEmpty(employeeDto.getLastName())){
            error += "lastName, " ;
        }
        if (StringUtils.isEmpty(employeeDto.getEmail())){
            error += "email, " ;
        }
        if (employeeDto.getHireDate()==null){
            error += "hireDate, " ;
        }
        if (employeeDto.getJob() == null){
            error += "jobId" ;
        }

        if(!error.isEmpty()){
            throw new ValidationException(messages.get("error.validacion.parametrosObligatorios", error));
        }
    }

}
