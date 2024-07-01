package com.aremi.frontend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class JwtToken {
    private String token;
    private Date expiresAt;
    private Date creationTime;
}
