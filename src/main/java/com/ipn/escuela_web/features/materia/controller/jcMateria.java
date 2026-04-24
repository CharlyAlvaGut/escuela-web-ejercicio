package com.ipn.escuela_web.features.materia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipn.escuela_web.core.domain.Materia;
import com.ipn.escuela_web.features.materia.service.IMateriaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("apiAlumnos/v1/materias")
public class jcMateria {

    @Autowired
    private IMateriaService materiaService;

    // ─── GET ALL ─────────────────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<?> findAll() {

        List<Materia> materias = null;
        Map<String, Object> response = new HashMap<>();

        try {
            materias = materiaService.findAll();
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos!!! :(");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        if (materias.isEmpty()) {
            response.put("mensaje", "No existen materias registradas en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return new ResponseEntity<List<Materia>>(materias, HttpStatus.OK);
    }

    // ─── GET BY ID ───────────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Materia materia = null;
        Map<String, Object> response = new HashMap<>();

        try {
            materia = materiaService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos!!! :(");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        if (materia == null) {
            response.put("mensaje", "La materia con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return new ResponseEntity<Materia>(materia, HttpStatus.OK);
    }

    // ─── CREATE ──────────────────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Materia materia) {

        Materia materiaNueva = null;
        Map<String, Object> response = new HashMap<>();

        try {
            materiaNueva = materiaService.save(materia);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar la materia en la base de datos!!! :(");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        response.put("mensaje", "La materia ha sido creada con éxito!");
        response.put("materia", materiaNueva);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // ─── UPDATE ──────────────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Materia materia, @PathVariable Long id) {

        Materia materiaActual = materiaService.findById(id);
        Materia materiaActualizada = null;
        Map<String, Object> response = new HashMap<>();

        if (materiaActual == null) {
            response.put("mensaje", "La materia con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        try {
            materiaActual.setNombre(materia.getNombre());
            materiaActual.setCreditos(materia.getCreditos());
            materiaActual.setDescripcion(materia.getDescripcion());
            materiaActual.setActivo(materia.isActivo());

            materiaActualizada = materiaService.save(materiaActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la materia en la base de datos!!! :(");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        response.put("mensaje", "La materia ha sido actualizada con éxito!");
        response.put("materia", materiaActualizada);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    // ─── DELETE ──────────────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Materia materiaExistente = materiaService.findById(id);

            if (materiaExistente == null) {
                response.put("mensaje", "La materia con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            materiaService.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la materia en la base de datos!!! :(");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        response.put("mensaje", "La materia ha sido eliminada con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}