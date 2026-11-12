package com.web.actividad4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "materias")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;
}