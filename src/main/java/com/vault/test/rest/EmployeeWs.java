package com.vault.test.rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import com.vault.test.rest.dto.EmployeeDto;
import com.vault.test.model.exception.ValidationException;
import com.vault.test.rest.dto.RespuestaGetEmployeeById;
import com.vault.test.rest.dto.RespuestaGetEmployees;
import com.vault.test.rest.dto.SolicitudGetEmployeesList;
import com.vault.test.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class EmployeeWs {

    final static Logger LOGGER = Logger.getLogger(EmployeeWs.class);

    @Inject
    private EmployeeService employeeService;

    @ModelAttribute
    public void setHeader(HttpServletResponse response) {
        response.setHeader("Vary", "Accept");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    }

    @RequestMapping(value = "/createOrReplaceEmployee", method = RequestMethod.POST, produces = "application/json")
    public RespuestaRestService createOrReplaceEmployee(@RequestBody EmployeeDto employeeDto) {
        LOGGER.info("Inicio createOrReplaceEmployee: " + new Date());
        RespuestaRestService respuestaRestService = new RespuestaRestService();

            try {
                employeeService.createOrUpdate(employeeDto);
            } catch (ValidationException e) {
                respuestaRestService.addError(e.getMessage());
                respuestaRestService.setRespuestaServicio(RespuestaRestService.RespuestaServicio.VALIDATION_ERROR);
                LOGGER.error(e.getMessage(), e);
            } catch (Exception e) {
                respuestaRestService.addError(e.getMessage());
                respuestaRestService.setRespuestaServicio(RespuestaRestService.RespuestaServicio.ERROR);
                LOGGER.error(e.getMessage(), e);
            }

        LOGGER.info("Final createOrReplaceEmployee: " + new Date());
        return respuestaRestService;
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST, produces = "application/json")
    public RespuestaRestService deleteEmployee(@RequestBody Long employeeId) {
        LOGGER.info("Inicio deleteEmployee: " + new Date());
        RespuestaRestService respuestaRestService = new RespuestaRestService();

        try {
            employeeService.delete(employeeId);
        } catch (Exception e) {
            respuestaRestService.addError(e.getMessage());
            respuestaRestService.setRespuestaServicio(RespuestaRestService.RespuestaServicio.ERROR);
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Final deleteEmployee: " + new Date());
        return respuestaRestService;
    }

    @RequestMapping(value = "/getEmployeeById", method = RequestMethod.POST, produces = "application/json")
    public RespuestaGetEmployeeById getEmployee(@RequestBody Long employeeId) {
        LOGGER.info("Inicio getEmployeeById: " + new Date());
        RespuestaGetEmployeeById respuestaGetEmployeeById = new RespuestaGetEmployeeById();

        try {
            respuestaGetEmployeeById.setEmployeeDto(employeeService.getById(employeeId));
        } catch (Exception e) {
            respuestaGetEmployeeById.addError(e.getMessage());
            respuestaGetEmployeeById.setRespuestaServicio(RespuestaRestService.RespuestaServicio.ERROR);
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Final getEmployeeById: " + new Date());
        return respuestaGetEmployeeById;

    }

    @RequestMapping(value = "/getEmployeesList", method = RequestMethod.POST, produces = "application/json")
    public RespuestaGetEmployees getEmployeesList(@RequestBody SolicitudGetEmployeesList solicitudGetEmployeesList) {
        LOGGER.info("Inicio getEmployeesList: " + new Date());
        RespuestaGetEmployees respuestaGetEmployees = new RespuestaGetEmployees();

        try {
            respuestaGetEmployees.setRespuesta(employeeService.getEmployees(solicitudGetEmployeesList));
        } catch (Exception e) {
            respuestaGetEmployees.addError(e.getMessage());
            respuestaGetEmployees.setRespuestaServicio(RespuestaRestService.RespuestaServicio.ERROR);
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Final getEmployeesList: " + new Date());
        return respuestaGetEmployees;
    }

}
