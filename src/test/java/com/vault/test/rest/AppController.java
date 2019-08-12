package com.vault.controllers;

import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    final static Logger logger = Logger.getLogger(AppController.class);

//    @Autowired
//    private AppServiceImpl appService;

    @ModelAttribute
    public void setHeader(HttpServletResponse response) {
        response.setHeader("Vary", "Accept");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    }

    @RequestMapping(value = "/{prenda}", method = RequestMethod.GET, produces = "application/json")
    public String getOpinionPrenda(@PathVariable String prenda) {
        logger.info("comienza getOpinionPrenda");
        String respuesta = "Tu "+ prenda + " es muy piola.";
        logger.info("se envia respuesta getOpinionPrenda");
        return respuesta;
    }

    @RequestMapping(value = "/opinarSobrePrenda", method = RequestMethod.POST, produces = "application/json")
    public String postOpinionPrenda(@RequestBody String opinion) {
        String respuesta = "Gracias por opinar sobre "+ opinion +" .";
        return respuesta;
    }
}
