package com.aremi.frontend.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class GenericResponse<T> {
    private List<T> entities;
    private int entitiesNumber;
    private HttpStatus httpCode;
    private String description;
}
