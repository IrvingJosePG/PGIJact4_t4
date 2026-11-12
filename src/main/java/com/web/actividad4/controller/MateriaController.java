package com.web.actividad4.controller;

import com.web.actividad4.dto.MateriaDTO;
import com.web.actividad4.entity.Materia;
import com.web.actividad4.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    // 1. GET - Listar con paginación
    @GetMapping
    public ResponseEntity<Page<Materia>> listarMaterias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Materia> materias = materiaService.listarTodas(PageRequest.of(page, size));
        return new ResponseEntity<>(materias, HttpStatus.OK);
    }

    // 2. GET - Obtener una por ID
    @GetMapping("/{id}")
    public ResponseEntity<Materia> obtenerMateria(@PathVariable Long id) {
        Optional<Materia> materia = materiaService.obtenerPorId(id);
        return materia.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 3. POST - Crear (Validado con DTO)
    @PostMapping
    public ResponseEntity<Materia> crearMateria(@Valid @RequestBody MateriaDTO materiaDTO) {
        Materia nuevaMateria = new Materia();
        nuevaMateria.setNombre(materiaDTO.getNombre());
        Materia materiaGuardada = materiaService.guardar(nuevaMateria);
        return new ResponseEntity<>(materiaGuardada, HttpStatus.CREATED);
    }

    // 4. PUT - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Materia> actualizarMateria(@PathVariable Long id, @Valid @RequestBody MateriaDTO materiaDTO) {
        Optional<Materia> materiaExistente = materiaService.obtenerPorId(id);
        
        if (materiaExistente.isPresent()) {
            Materia materia = materiaExistente.get();
            materia.setNombre(materiaDTO.getNombre());
            return new ResponseEntity<>(materiaService.guardar(materia), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. DELETE - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMateria(@PathVariable Long id) {
        if (materiaService.obtenerPorId(id).isPresent()) {
            materiaService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }
}