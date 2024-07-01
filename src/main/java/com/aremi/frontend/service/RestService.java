package com.aremi.frontend.service;

import com.aremi.frontend.dto.GenericResponse;
import com.aremi.frontend.dto.JwtToken;
import com.aremi.frontend.dto.bean.DipendenteBean;
import com.aremi.frontend.dto.bean.UtenteBean;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
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

    public GenericResponse<JwtToken> authenticate(UtenteBean user) {
        logger.info("RestService::authenticate service started... sending REST to microservice");
        GenericResponse<JwtToken> finalResponse = new GenericResponse<>();
        try {
            ResponseEntity<Void> response = webClient.post()
                    .uri("/api/login")
                    .bodyValue(user)
                    .retrieve()
                    .toEntity(Void.class)
                    .block();

            if(!Objects.isNull(response)) {
                JwtToken jwtToken = new JwtToken();
                jwtToken.setToken(response.getHeaders().getFirst("Authorization"));
                jwtToken.setCreationTime(new Date(response.getHeaders().getFirstDate("Date")));

                String jwtExpirationDateString = response.getHeaders().getFirst("JWT-Expiration-Date");
                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                Date jwtExpirationDate = format.parse(jwtExpirationDateString);
                jwtToken.setExpiresAt(jwtExpirationDate);

                logger.info("RestService::authenticate Response retrived from microservice:\n" + jwtToken);
                finalResponse.getEntities().add(jwtToken);
            } else {
                logger.severe("RestService::authenticate Error... response is null");
            }
        } catch (Exception e) {
            logger.severe("RestService::authenticate Error...\n" + e.getMessage());
        }
        return finalResponse;
    }
}
