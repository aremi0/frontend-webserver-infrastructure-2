package com.aremi.frontend.controller;

import com.aremi.frontend.dto.GenericResponse;
import com.aremi.frontend.dto.bean.DipendenteBean;
import com.aremi.frontend.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public final class MainController {
    private final Logger logger = Logger.getLogger("RestController_Logger");
    private final RestService restService;

    @Autowired
    public MainController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/dipendente/{id}")
    GenericResponse<DipendenteBean> getDipendenteById(@PathVariable("id") Long id) {
        logger.info("RestController::getDipendenteById REST received from client with ID: " + id);
        return restService.getDipendenteBeanById(id);
    }

    @GetMapping("/dipendentiBySede/{id_sede}")
    GenericResponse<DipendenteBean> getDipendentiByIdSede(@PathVariable("id_sede") Long idSede) {
        logger.info("RestController::getDipendentiByIdSede REST received from client with ID: " + idSede);
        return restService.getDipendentibeanByIdSede(idSede);
    }
}
