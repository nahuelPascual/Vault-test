package com.vault.test;

import com.vault.test.rest.dto.EmployeeDto;

import com.vault.test.model.exception.ValidationException;
import com.vault.test.service.EmployeeService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class EmployeeTests {

    private static final Logger LOGGER = Logger.getLogger(EmployeeTests.class);

    @Inject
    private EmployeeService employeeService;

    @Test
    public void insertNewEmployeeWithMandatoryFields(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setLastName("AnyLastName");
        employeeDto.setEmail("AnyEmail");
        employeeDto.setHireDate(new Date());
        employeeDto.setJob(1L);

        try{
            employeeService.createOrUpdate(employeeDto);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            Assert.fail();
        }
    }

    @Test
    public void validationNewEmployeeWithoutEmail(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setLastName("AnyLastName");
        employeeDto.setHireDate(new Date());
        employeeDto.setJob(1L);

         createOrUpdateThrowsValidationException(employeeDto);
    }

    private void createOrUpdateThrowsValidationException(EmployeeDto employeeDto){
        boolean ok = false;
        try {
            employeeService.createOrUpdate(employeeDto);
        } catch (ValidationException e) {
            LOGGER.info(e.getMessage(),e);
            ok = true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }

        if(!ok){
            Assert.fail();
        }
    }

    @Test
    public void validationNewEmptymEployee(){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Prueba");

        createOrUpdateThrowsValidationException(employeeDto);
    }

    @Test
    public void deleteExistentEmployee(){
        try{
            employeeService.delete(22L);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            Assert.fail();
        }
    }

    @Test
    public void deleteInexistentEmployee(){
        boolean ok = false;

        try {
            employeeService.delete(-1L);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            ok = true;
        }

        if(!ok){
            Assert.fail();
        }
    }

    @Test
    public void getInextistentEmployee(){
        boolean ok = false;

        try{
            employeeService.getById(-1L);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            ok = true;
        }

        if(!ok) {
            Assert.fail();
        }
    }
}
