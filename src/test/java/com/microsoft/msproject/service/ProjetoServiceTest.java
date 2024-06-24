package com.microsoft.msproject.service;

import com.microsoft.msproject.model.Projeto;
import com.microsoft.msproject.repository.ProjetoRepository;
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

public class ProjetoServiceTest {
    @InjectMocks
    private ProjetoService projetoService;

    @Mock
    private ProjetoRepository projetoRepository;

    @Mock
    private AtividadeService atividadeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllOpenProjects() {
        // Mock data
        Projeto projeto1 = new Projeto();
        projeto1.setId(1L);
        projeto1.setTitulo("Projeto 1");

        Projeto projeto2 = new Projeto();
        projeto2.setId(2L);
        projeto2.setTitulo("Projeto 2");

        List<Projeto> projetos = Arrays.asList(projeto1, projeto2);

        when(projetoRepository.findAllOpenProjects(anyLong())).thenReturn(projetos);

        List<Projeto> result = projetoService.findAllOpenProjects(1L);

        assertEquals(2, result.size());
        assertEquals("Projeto 1", result.get(0).getTitulo());
        assertEquals("Projeto 2", result.get(1).getTitulo());
    }

    @Test
    public void testGetAllProjetos() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setTitulo("Projeto 1");

        Projeto projeto2 = new Projeto();
        projeto2.setId(2L);
        projeto2.setTitulo("Projeto 2");

        List<Projeto> projetos = Arrays.asList(projeto, projeto2);

        when(projetoRepository.findAll()).thenReturn(projetos);

        List<Projeto> result = projetoService.getAll();

        assertEquals(2, result.size());
        assertEquals("Projeto 1", result.get(0).getTitulo());
        assertEquals("Projeto 2", result.get(1).getTitulo());
    }

    @Test
    public void testGetProjetoById() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setTitulo("Projeto 1");

        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));

        Optional<Projeto> result = projetoService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Projeto 1", result.get().getTitulo());
    }

    @Test
    public void testCreateProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setTitulo("Projeto 1");

        when(projetoRepository.save(projeto)).thenReturn(projeto);

        Projeto result = projetoService.save(projeto);

        assertEquals("Projeto 1", result.getTitulo());
    }

    @Test
    public void testUpdateProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setTitulo("Projeto 1");

        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));
        when(projetoRepository.save(any(Projeto.class))).thenReturn(projeto);

        Projeto result = projetoService.save(projeto);

        assertEquals("Projeto 1", result.getTitulo());
    }

    @Test
    public void testDeleteProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1L);
        projeto.setTitulo("Projeto 1");

        when(projetoRepository.findById(anyLong())).thenReturn(Optional.of(projeto));
        doNothing().when(projetoRepository).deleteById(1L);

        projetoService.delete(1L);

        verify(projetoRepository, times(1)).deleteById(1L);
    }
}
