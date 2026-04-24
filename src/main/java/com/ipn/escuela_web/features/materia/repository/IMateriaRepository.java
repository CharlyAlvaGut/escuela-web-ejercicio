package com.ipn.escuela_web.features.materia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ipn.escuela_web.core.domain.Materia;

public interface IMateriaRepository extends JpaRepository<Materia, Long> {

}