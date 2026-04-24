package com.ipn.escuela_web.features.alumno.controller;

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

import com.ipn.escuela_web.core.domain.Alumno;
import com.ipn.escuela_web.features.alumno.service.IAlumnoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("apiAlumnos/v1/alumnos")
public class jcAlumno {

    @Autowired
    private IAlumnoService alumnoService;

    // ─── GET ALL ────────────────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<?> findAll() {

        List<Alumno> alumnos = null;
        Map<String, Object> response = new HashMap<>();

        try {
            alumnos = alumnoService.findAll();
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos!!! :(");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        if (alumnos.isEmpty()) {
            response.put("mensaje", "No existen alumnos registrados en la base de datos");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return new ResponseEntity<List<Alumno>>(alumnos, HttpStatus.OK);
    }

    // ─── GET BY ID ───────────────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Alumno alumno = null;
        Map<String, Object> response = new HashMap<>();

        try {
            alumno = alumnoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos!!! :(");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        if (alumno == null) {
            response.put("mensaje", "El alumno con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
    }

    // ─── CREATE ──────────────────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Alumno alumno) {

        Alumno alumnoNuevo = null;
        Map<String, Object> response = new HashMap<>();

        try {
            alumnoNuevo = alumnoService.save(alumno);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el alumno en la base de datos!!! :(");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        response.put("mensaje", "El alumno ha sido creado con éxito!");
        response.put("alumno", alumnoNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // ─── UPDATE ──────────────────────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Alumno alumno, @PathVariable Long id) {

        Alumno alumnoActual = alumnoService.findById(id);
        Alumno alumnoActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if (alumnoActual == null) {
            response.put("mensaje", "El alumno con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        try {
            alumnoActual.setNombre(alumno.getNombre());
            alumnoActual.setPaterno(alumno.getPaterno());
            alumnoActual.setMaterno(alumno.getMaterno());
            alumnoActual.setEmail(alumno.getEmail());
            
        		
            alumnoActualizado = alumnoService.save(alumnoActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el alumno en la base de datos!!! :(");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        response.put("mensaje", "El alumno ha sido actualizado con éxito!");
        response.put("alumno", alumnoActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    // ─── DELETE ──────────────────────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            Alumno alumnoExistente = alumnoService.findById(id);

            if (alumnoExistente == null) {
                response.put("mensaje", "El alumno con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            alumnoService.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el alumno en la base de datos!!! :(");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        response.put("mensaje", "El alumno ha sido eliminado con éxito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}