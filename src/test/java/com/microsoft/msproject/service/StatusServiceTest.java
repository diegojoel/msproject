package com.microsoft.msproject.service;

import com.microsoft.msproject.model.Status;
import com.microsoft.msproject.repository.StatusRepository;
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

public class StatusServiceTest {
    @InjectMocks
    private StatusService statusService;

    @Mock
    private StatusRepository statusRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllStatus() {
        Status status1 = new Status();
        status1.setId(1L);
        status1.setStatus("Em Andamento");

        Status status2 = new Status();
        status2.setId(2L);
        status2.setStatus("Cancelado");

        List<Status> statuss = Arrays.asList(status1, status2);

        when(statusRepository.findAll()).thenReturn(statuss);

        List<Status> result = statusService.getAll();

        assertEquals(2, result.size());
        assertEquals("Em Andamento", result.get(0).getStatus());
        assertEquals("Cancelado", result.get(1).getStatus());
    }

    @Test
    public void testGetStatusById() {
        Status status = new Status();
        status.setId(1L);
        status.setStatus("Em Andamento");

        when(statusRepository.findById(anyLong())).thenReturn(Optional.of(status));

        Optional<Status> result = statusService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("Em Andamento", result.get().getStatus());
    }

    @Test
    public void testCreateStatus() {
        Status status = new Status();
        status.setId(1L);
        status.setStatus("Em Andamento");

        when(statusRepository.save(status)).thenReturn(status);

        Status result = statusService.save(status);

        assertEquals("Em Andamento", result.getStatus());
    }

    @Test
    public void testUpdateStatus() {
        Status status = new Status();
        status.setId(1L);
        status.setStatus("Em Andamento");

        when(statusRepository.findById(anyLong())).thenReturn(Optional.of(status));
        when(statusRepository.save(any(Status.class))).thenReturn(status);

        Status result = statusService.save(status);

        assertEquals("Em Andamento", result.getStatus());
    }

    @Test
    public void testDeleteStatus() {
        Status status = new Status();
        status.setId(1L);
        status.setStatus("Em Andamento");

        when(statusRepository.findById(anyLong())).thenReturn(Optional.of(status));
        doNothing().when(statusRepository).deleteById(1L);

        statusService.delete(1L);

        verify(statusRepository, times(1)).deleteById(1L);
    }
}
