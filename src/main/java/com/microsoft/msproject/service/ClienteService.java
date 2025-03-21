package com.microsoft.msproject.service;
import com.microsoft.msproject.model.Cliente;
import com.microsoft.msproject.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente client) {
        return clienteRepository.save(client);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
