package com.aremi.frontend.service;

import com.aremi.frontend.dto.GenericResponse;
import com.aremi.frontend.dto.bean.DipendenteBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

@Service
public class RestService {
    private final WebClient webClient;

    public RestService() {
        // TODO: aggiornare l'uri in base al layer infrastrutturale di spring
        this.webClient = WebClient.create("http://microservizio-spring:8083");
    }

    // TODO: aggiornare l'uri dell'endpoint a cui mandare la request
    public GenericResponse<DipendenteBean> getDipendenteBeanById(Long id) {
        return webClient.get()
                .uri("/endpoint-del-microservizio-Spring ")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<GenericResponse<DipendenteBean>>() {})
                .block();
    }
}
