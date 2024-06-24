package com.microsoft.msproject.repository;

import com.microsoft.msproject.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante,Long> {
}
