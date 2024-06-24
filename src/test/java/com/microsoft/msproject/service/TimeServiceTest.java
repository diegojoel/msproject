package com.microsoft.msproject.service;

import com.microsoft.msproject.model.Time;
import com.microsoft.msproject.repository.TimeRepository;
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

public class TimeServiceTest {
    @InjectMocks
    private TimeService timeService;

    @Mock
    private TimeRepository timeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTime() {
        Time time = new Time();
        time.setId(1L);
        time.setNome("TI");

        Time time2 = new Time();
        time2.setId(2L);
        time2.setNome("RH");

        List<Time> times = Arrays.asList(time, time2);

        when(timeRepository.findAll()).thenReturn(times);

        List<Time> result = timeService.getAll();

        assertEquals(2, result.size());
        assertEquals("TI", result.get(0).getNome());
        assertEquals("RH", result.get(1).getNome());
    }

    @Test
    public void testGetTimeById() {
        Time time = new Time();
        time.setId(1L);
        time.setNome("TI");

        when(timeRepository.findById(anyLong())).thenReturn(Optional.of(time));

        Optional<Time> result = timeService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals("TI", result.get().getNome());
    }

    @Test
    public void testCreateTime() {
        Time time = new Time();
        time.setId(1L);
        time.setNome("TI");

        when(timeRepository.save(time)).thenReturn(time);

        Time result = timeService.save(time);

        assertEquals("TI", result.getNome());
    }

    @Test
    public void testUpdateTime() {
        Time time = new Time();
        time.setId(1L);
        time.setNome("TI");

        when(timeRepository.findById(anyLong())).thenReturn(Optional.of(time));
        when(timeService.save(any(Time.class))).thenReturn(time);

        Time result = timeService.save(time);

        assertEquals("TI", result.getNome());
    }

    @Test
    public void testDeleteTime() {
        Time time = new Time();
        time.setId(1L);
        time.setNome("TI");

        when(timeRepository.findById(anyLong())).thenReturn(Optional.of(time));
        doNothing().when(timeRepository).deleteById(1L);

        timeService.delete(1L);

        verify(timeRepository, times(1)).deleteById(1L);
    }
}
