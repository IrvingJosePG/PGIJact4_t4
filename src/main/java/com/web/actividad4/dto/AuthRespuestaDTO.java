package com.web.actividad4.dto;

import lombok.Data;

@Data
public class AuthRespuestaDTO {
    private String token;
    private String tipoToken = "Bearer ";

    public AuthRespuestaDTO(String token) {
        this.token = token;
    }
}