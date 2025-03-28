package com.microsoft.msproject.service;
import com.microsoft.msproject.model.Participante;
import com.microsoft.msproject.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    public List<Participante> getAll() {
        return participanteRepository.findAll();
    }

    public Optional<Participante> getById(Long id) {
        return participanteRepository.findById(id);
    }

    public Participante save(Participante participante) {
        return participanteRepository.save(participante);
    }

    public void delete(Long id) {participanteRepository.deleteById(id);}
}
