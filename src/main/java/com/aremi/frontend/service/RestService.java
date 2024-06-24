package com.aremi.frontend.service;

import com.aremi.frontend.dto.GenericResponse;
import com.aremi.frontend.dto.bean.DipendenteBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RestService {
    private final Logger logger;
    private final WebClient webClient;

    public RestService() {
        logger = Logger.getLogger("RestService_Logger");
        this.webClient = WebClient.create("http://microservizio:8083");
    }

    public GenericResponse<DipendenteBean> getDipendenteBeanById(Long id) {
        logger.info("RestService::getDipendenteBeanById service started... sending REST to request-translator microservice");
        try {
            return webClient.get()
                    .uri("/api/dipendente/{id}", id)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<GenericResponse<DipendenteBean>>() {})
                    .block();
        } catch (Exception e) {
            logger.info("RestService::getDipendenteBeanById Error...\n" + e.getMessage());
        }

        return null;
    }

    public GenericResponse<DipendenteBean> getDipendentibeanByIdSede(Long idSede) {
        logger.info("RestService::getDipendentiBeanByIdSede service started... sending REST to request-translator microservice");
        try {
            return webClient.get()
                    .uri("/api/dipendentiBySede/{id_sede}", idSede)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<GenericResponse<DipendenteBean>>() {})
                    .block();
        } catch (Exception e) {
            logger.info("RestService::getDipendentiBeanByIdSede Error...\n" + e.getMessage());
        }

        return null;
    }
}
