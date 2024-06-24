package com.microsoft.msproject.service;

import com.microsoft.msproject.model.Participante;
import com.microsoft.msproject.repository.ParticipanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ParticipanteServiceTest {
    @InjectMocks
    private ParticipanteService participanteService;

    @Mock
    private ParticipanteRepository participanteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllParticipante() {
        Participante participante = new Participante();
        participante.setId(1L);
        participante.setNome("Diego");

        Participante participante2 = new Participante();
        participante2.setId(2L);
        participante2.setNome("Matheus");

        List<Participante> participantes = Arrays.asList(participante, participante2);

        when(participanteRepository.findAll()).thenReturn(participantes);

        List<Participante> result = participanteService.getAll();

        assertEquals(2, result.size());
        assertEquals("Diego", result.get(0).getNome());
        assertEquals("Matheus", result.get(1).getNome());
    }

    @Test
    public void testGetParticipanteById() {
        Participante participante = new Participante();
        participante.setId(1L);
        participante.setNome("Diego");

        when(participanteRepository.findById(anyLong())).thenReturn(Optional.of(participante));

        Optional<Participante> result = participanteRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Diego", result.get().getNome());
    }

    @Test
    public void testCreateParticipante() {
        Participante participante = new Participante();
        participante.setId(1L);
        participante.setNome("Diego");

        when(participanteRepository.save(participante)).thenReturn(participante);

        Participante result = participanteService.save(participante);

        assertEquals("Diego", result.getNome());
    }

    @Test
    public void testUpdateTime() {
        Participante participante = new Participante();
        participante.setId(1L);
        participante.setNome("Diego");

        when(participanteRepository.findById(anyLong())).thenReturn(Optional.of(participante));
        when(participanteRepository.save(any(Participante.class))).thenReturn(participante);

        Participante result = participanteRepository.save(participante);

        assertEquals("Diego", result.getNome());
    }

    @Test
    public void testDeleteTime() {
        Participante participante = new Participante();
        participante.setId(1L);
        participante.setNome("Diego");

        when(participanteRepository.findById(anyLong())).thenReturn(Optional.of(participante));
        doNothing().when(participanteRepository).deleteById(1L);

        participanteService.delete(1L);

        verify(participanteRepository, times(1)).deleteById(1L);
    }
}
