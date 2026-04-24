package com.ipn.escuela_web.features.materia.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipn.escuela_web.core.domain.Materia;
import com.ipn.escuela_web.features.materia.repository.IMateriaRepository;
import com.ipn.escuela_web.features.materia.service.IMateriaService;

@Service
public class MateriaServiceImp implements IMateriaService {

    @Autowired
    private IMateriaRepository materiaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Materia> findAll() {
        return (List<Materia>) materiaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Materia findById(Long id) {
        return materiaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Materia save(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        materiaRepository.deleteById(id);
    }
}