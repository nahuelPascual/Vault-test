package com.vault.test.model.dao.impl;

import com.vault.test.model.dao.EmployeeDao;
import com.vault.test.model.dao.JpaEntityDaoImpl;
import com.vault.test.model.dao.QueryBuilder;
import com.vault.test.model.entity.Employee;
import com.vault.test.model.exception.DaoException;
import com.vault.test.model.exception.ValidationException;
import com.vault.test.model.utils.Messages;
import com.vault.test.rest.dto.SolicitudGetEmployeesList;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class EmployeeDaoImpl extends JpaEntityDaoImpl <Long, Employee> implements EmployeeDao {

    @Inject
    private QueryBuilder queryBuilder;

    @Inject
    private Messages messages;

    @Override
    public Double getAvgSalaryLocation(Long locationId) throws DaoException {
        return getEntityManager()
                .createNamedQuery(Employee.GET_SALARIES_QUERY, Double.class)
                .setParameter("locationId", locationId)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> getEmployeeList(SolicitudGetEmployeesList solicitudGetEmployeesList) throws DaoException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("jobId", solicitudGetEmployeesList.getJobId());
        parameters.put("managerId", solicitudGetEmployeesList.getManagerId());
        parameters.put("lastName", solicitudGetEmployeesList.getLastName());
        return (List<Employee>) queryBuilder
                .createQuery(Employee.GET_EMPLOYEES_QUERY_ST,  parameters, solicitudGetEmployeesList.getRegistroInicial(),
                        solicitudGetEmployeesList.getCantidadRegistros());
    }

    public Employee getEmployee (Long id) throws DaoException, ValidationException {
        Employee e = findById(id);
        if(e == null) {
            throw new ValidationException(messages.get("error.validation.employeeInexistente"));
        }
        return e;
    }

}
