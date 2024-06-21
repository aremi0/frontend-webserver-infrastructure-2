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
        // TODO: aggiornare l'uri in base al layer infrastrutturale di spring
        this.webClient = WebClient.create("http://microservizio:8083");
    }

    // TODO: aggiornare l'uri dell'endpoint a cui mandare la request
    public GenericResponse<DipendenteBean> getDipendenteBeanById(Long id) {
        logger.info("RestService::getDipendenteBeanById started...");
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
}
