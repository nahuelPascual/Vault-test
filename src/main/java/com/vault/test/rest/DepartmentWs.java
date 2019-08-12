package com.vault.test.rest;

import com.vault.test.model.exception.ValidationException;
import com.vault.test.rest.dto.*;
import com.vault.test.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class DepartmentWs {

    final static Logger LOGGER = Logger.getLogger(DepartmentWs.class);

    @Inject
    private DepartmentService departmentService;

    @ModelAttribute
    public void setHeader(HttpServletResponse response) {
        response.setHeader("Vary", "Accept");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    }

    @RequestMapping(value = "/createDepartment", method = RequestMethod.POST, produces = "application/json")
    public RespuestaRestService createDepartment(@RequestBody DepartmentDto departmentDto) {
        LOGGER.info("Inicio createDepartment: " + new Date());
        RespuestaRestService respuestaRestService = new RespuestaRestService();

            try {
                departmentService.create(departmentDto);
            } catch (ValidationException e) {
                respuestaRestService.addError(e.getMessage());
                respuestaRestService.setRespuestaServicio(RespuestaRestService.RespuestaServicio.VALIDATION_ERROR);
                LOGGER.error(e.getMessage(), e);
            } catch (Exception e) {
                respuestaRestService.addError(e.getMessage());
                respuestaRestService.setRespuestaServicio(RespuestaRestService.RespuestaServicio.ERROR);
                LOGGER.error(e.getMessage(), e);
            }

        LOGGER.info("Final createDepartment: " + new Date());
        return respuestaRestService;
    }


}
