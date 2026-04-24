package com.ipn.escuela_web.features.alumno.service;

import com.ipn.escuela_web.core.domain.Alumno;
import java.util.List;

public interface IAlumnoService {

    public List<Alumno> findAll();
    public Alumno findById(Long id);
    public Alumno save(Alumno alumno);
    public void deleteById(Long id);
}