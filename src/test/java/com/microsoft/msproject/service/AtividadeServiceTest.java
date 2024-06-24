package com.microsoft.msproject.service;

import com.microsoft.msproject.model.Atividade;
import com.microsoft.msproject.repository.AtividadeRepository;
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

public class AtividadeServiceTest {

    @InjectMocks
    private AtividadeService atividadeService;

    @Mock
    private AtividadeRepository atividadeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAtividades() {
        Atividade atividade1 = new Atividade();
        atividade1.setId(1L);
        atividade1.setAtividade("Atividade 1");

        Atividade atividade2 = new Atividade();
        atividade2.setId(2L);
        atividade2.setAtividade("Atividade 2");

        List<Atividade> atividades = Arrays.asList(atividade1, atividade2);

        when(atividadeRepository.findAll()).thenReturn(atividades);

        List<Atividade> result = atividadeService.getAll();

        assertEquals(2, result.size());
        assertEquals("Atividade 1", result.get(0).getAtividade());
        assertEquals("Atividade 2", result.get(1).getAtividade());
    }

    @Test
    public void testGetAtividadeById() {
        Atividade atividade = new Atividade();
        atividade.setId(1L);
        atividade.setAtividade("Atividade 1");

        when(atividadeRepository.findById(anyLong())).thenReturn(Optional.of(atividade));

        Optional<Atividade> result = atividadeService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Atividade 1", result.get().getAtividade());
    }

    @Test
    public void testCreateAtividade() {
        Atividade atividade = new Atividade();
        atividade.setId(1L);
        atividade.setAtividade("Atividade 1");

        when(atividadeRepository.save(atividade)).thenReturn(atividade);

        Atividade result = atividadeService.save(atividade);

        assertEquals("Atividade 1", result.getAtividade());
    }

    @Test
    public void testUpdateAtividade() {
        Atividade atividade = new Atividade();
        atividade.setId(1L);
        atividade.setAtividade("Atividade 1");

        when(atividadeRepository.findById(anyLong())).thenReturn(Optional.of(atividade));
        when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

        Atividade result = atividadeService.save(atividade);

        assertEquals("Atividade 1", result.getAtividade());
    }

    @Test
    public void testDeleteAtividade() {
        Atividade atividade = new Atividade();
        atividade.setId(1L);
        atividade.setAtividade("Atividade 1");

        when(atividadeRepository.findById(anyLong())).thenReturn(Optional.of(atividade));
        doNothing().when(atividadeRepository).deleteById(1L);

        atividadeService.delete(1L);

        verify(atividadeRepository, times(1)).deleteById(1L);
    }
}
