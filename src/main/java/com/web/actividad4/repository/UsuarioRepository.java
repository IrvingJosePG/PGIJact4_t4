package com.web.actividad4.repository;

import com.web.actividad4.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Método de Spring Data JPA: Busca un usuario por su email
    Optional<Usuario> findByEmail(String email);
}