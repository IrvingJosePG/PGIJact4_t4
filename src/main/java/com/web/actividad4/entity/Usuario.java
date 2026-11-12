package com.web.actividad4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data // Anotación de Lombok que genera getters, setters, toString, etc.
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true) // El correo no puede repetirse
    private String email;

    @Column(nullable = false)
    private String password; // Se guarda la contraseña encriptada
}