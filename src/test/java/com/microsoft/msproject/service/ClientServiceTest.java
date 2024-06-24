package com.microsoft.msproject.service;

import com.microsoft.msproject.model.Cliente;
import com.microsoft.msproject.repository.ClienteRepository;
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
import static org.mockito.Mockito.times;

public class ClientServiceTest {
    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Cliente 2");

        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.getAll();

        assertEquals(2, result.size());
        assertEquals("Cliente 1", result.get(0).getNome());
        assertEquals("Cliente 2", result.get(1).getNome());
    }

    @Test
    public void testGetClienteById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente 1");

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Cliente 1", result.get().getNome());
    }

    @Test
    public void testCreateCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente 1");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.save(cliente);

        assertEquals("Cliente 1", result.getNome());
    }

    @Test
    public void testUpdateCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente 1");

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente result = clienteService.save(cliente);

        assertEquals("Cliente 1", result.getNome());
    }

    @Test
    public void testDeleteCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente 1");

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.delete(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
