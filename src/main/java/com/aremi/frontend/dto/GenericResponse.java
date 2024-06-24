package com.aremi.frontend.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenericResponse<T> {
    private List<T> entities;
    private int entitiesNumber;
    private Integer httpCode;
    private String description;
}
