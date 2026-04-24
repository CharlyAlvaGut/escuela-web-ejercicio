package com.ipn.escuela_web.features.materia.service;

import com.ipn.escuela_web.core.domain.Materia;
import java.util.List;

public interface IMateriaService {

    public List<Materia> findAll();
    public Materia findById(Long id);
    public Materia save(Materia materia);
    public void deleteById(Long id);
}