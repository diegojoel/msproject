package com.microsoft.msproject.controller;

import com.microsoft.msproject.model.Participante;
import com.microsoft.msproject.service.ParticipanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ParticipanteControllerTest {
    @InjectMocks
    private ParticipanteController participanteController;

    @Mock
    private ParticipanteService participanteService;

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

        when(participanteService.getAll()).thenReturn(participantes);

        List<Participante> response = participanteController.getAll();

        assertEquals(2, response.size());
        assertEquals("Diego", response.get(0).getNome());
        assertEquals("Matheus", response.get(1).getNome());
    }

    @Test
    public void testGetParticipanteById() {
        Participante participante = new Participante();
        participante.setId(1L);
        participante.setNome("Diego");

        when(participanteService.getById(anyLong())).thenReturn(Optional.of(participante));

        ResponseEntity<Participante> response = participanteController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Diego", response.getBody().getNome());
    }

    @Test
    public void testCreateParticipante() {
        Participante participante = new Participante();
        participante.setId(1L);
        participante.setNome("Diego");

        when(participanteService.save(participante)).thenReturn(participante);

        Participante response = participanteController.create(participante);

        assertEquals("Diego", response.getNome());
    }

    @Test
    public void testUpdateTime() {
        Participante participante = new Participante();
        participante.setId(1L);
        participante.setNome("Diego");

        when(participanteService.getById(anyLong())).thenReturn(Optional.of(participante));
        when(participanteService.save(any(Participante.class))).thenReturn(participante);

        ResponseEntity<Participante> response = participanteController.update(1L, participante);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Diego", response.getBody().getNome());
    }

    @Test
    public void testDeleteTime() {
        Participante participante = new Participante();
        participante.setId(1L);
        participante.setNome("Diego");

        when(participanteService.getById(anyLong())).thenReturn(Optional.of(participante));
        doNothing().when(participanteService).delete(1L);

        ResponseEntity<Void> response = participanteController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(participanteService, times(1)).delete(1L);
    }
}
