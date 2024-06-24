package com.microsoft.msproject.service;
import com.microsoft.msproject.model.Projeto;
import com.microsoft.msproject.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projeto> getAll() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> getById(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto save(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public void delete(Long id) {
        projetoRepository.deleteById(id);
    }

    public List<Projeto> findAllOpenProjects(Long statusId){
        return projetoRepository.findAllOpenProjects(statusId);}
}
