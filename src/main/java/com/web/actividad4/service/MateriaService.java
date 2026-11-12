package com.web.actividad4.service;

import com.web.actividad4.entity.Materia;
import com.web.actividad4.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    // a) Listar con paginación
    public Page<Materia> listarTodas(Pageable pageable) {
        return materiaRepository.findAll(pageable);
    }

    // a) Ver un registro individual
    public Optional<Materia> obtenerPorId(Long id) {
        return materiaRepository.findById(id);
    }

    // b) Crear
    public Materia guardar(Materia materia) {
        return materiaRepository.save(materia);
    }

    // d) Eliminar
    public void eliminar(Long id) {
        materiaRepository.deleteById(id);
    }
}