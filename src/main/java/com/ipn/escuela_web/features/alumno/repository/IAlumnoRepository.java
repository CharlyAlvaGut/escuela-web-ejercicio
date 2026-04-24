package com.ipn.escuela_web.features.alumno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipn.escuela_web.core.domain.Alumno;

public interface IAlumnoRepository extends JpaRepository<Alumno, Long>{

}
