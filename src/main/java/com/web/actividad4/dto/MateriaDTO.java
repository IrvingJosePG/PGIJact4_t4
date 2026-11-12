package com.web.actividad4.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MateriaDTO {
    @NotBlank(message = "El nombre de la materia no puede estar vacío")
    private String nombre;
}