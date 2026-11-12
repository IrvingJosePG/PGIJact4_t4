package com.web.actividad4.repository;

import com.web.actividad4.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
}