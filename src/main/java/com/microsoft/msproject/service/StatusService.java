package com.microsoft.msproject.service;
import com.microsoft.msproject.model.Status;
import com.microsoft.msproject.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    public Optional<Status> getById(Long id) {
        return statusRepository.findById(id);
    }

    public Status save(Status status) {
        return statusRepository.save(status);
    }

    public void delete(Long id) {
        statusRepository.deleteById(id);
    }
}
