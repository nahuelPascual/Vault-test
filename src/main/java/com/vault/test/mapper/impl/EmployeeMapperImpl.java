package com.vault.test.mapper.impl;

import com.vault.test.mapper.EmployeeMapper;
import com.vault.test.model.dao.DepartmentDao;
import com.vault.test.model.dao.EmployeeDao;
import com.vault.test.model.dao.JobDao;
import com.vault.test.model.utils.DateUtility;
import com.vault.test.rest.dto.EmployeeDto;
import com.vault.test.model.entity.Employee;
import com.vault.test.model.exception.DaoException;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Named
public class EmployeeMapperImpl implements EmployeeMapper {

    private static final Logger LOGGER = Logger.getLogger(EmployeeMapperImpl.class);

    @Inject
    private DepartmentDao departmentDao;

    @Inject
    private JobDao jobDao;

    @Inject
    private EmployeeDao employeeDao;

    @Override
    public void map(EmployeeDto employeeDto, Employee employee) throws DaoException {
        employee.setComissionPct(employeeDto.getComissionPct());
        employee.setDepartment(departmentDao.findById(employeeDto.getDepartment()));
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setHireDate(employeeDto.getHireDate());
        employee.setJob(jobDao.findById(employeeDto.getJob()));
        employee.setManager(employeeDao.findById(employeeDto.getManager()));
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setSalary(employeeDto.getSalary());
    }

    @Override
    public void merge(EmployeeDto employeeDto, Employee employee) throws DaoException {
        employee.setComissionPct(employeeDto.getComissionPct() != null ?
                employeeDto.getComissionPct() : employee.getComissionPct());
        employee.setDepartment(employeeDto.getDepartment() != null ?
                departmentDao.findById(employeeDto.getDepartment()) : employee.getDepartment());
        employee.setEmail(employeeDto.getEmail() != null ?
                employeeDto.getEmail() : employee.getEmail());
        employee.setFirstName(employeeDto.getFirstName() != null ?
                employeeDto.getFirstName() : employee.getFirstName());
        employee.setLastName(employeeDto.getLastName() != null ?
                employeeDto.getLastName() : employee.getLastName());
        employee.setHireDate(employeeDto.getHireDate() != null ?
                employeeDto.getHireDate() : employee.getHireDate());
        employee.setJob(employeeDto.getJob() != null ?
            jobDao.findById(employeeDto.getJob()) : employee.getJob());
        employee.setManager(employeeDto.getManager() != null ?
                employeeDao.findById(employeeDto.getManager()) : employee.getManager());
        employee.setPhoneNumber(employeeDto.getPhoneNumber() != null ?
                employeeDto.getPhoneNumber() : employee.getPhoneNumber());
        employee.setSalary(employeeDto.getSalary() != null ?
                employeeDto.getSalary() : employee.getSalary());
    }

    @Override
    public void map(List<Employee> employees, List<EmployeeDto> employeeDtos) {
        employees.stream().forEach(e -> {
            EmployeeDto dto = new EmployeeDto();
            try{
                map(e, dto);
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
            employeeDtos.add(dto);
        } );
    }

    @Override
    public void map(Employee employee, EmployeeDto employeeDto) throws ParseException {
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setComissionPct(employee.getComissionPct());
        employeeDto.setDepartment(employee.getDepartment()!= null ? employee.getDepartment().getDepartmentId() : null);
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setHireDate(DateUtility.parseDate(employee.getHireDate(), DateUtility.PATRON_FECHA_HORA_CON_BARRAS));
        employeeDto.setJob(employee.getJob() != null ? employee.getJob().getJobId() : null);
        employeeDto.setManager(employee.getManager()!=null ? employee.getManager().getEmployeeId() : null);
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setSalary(employee.getSalary());
    }
}
