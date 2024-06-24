package com.microsoft.msproject.service;
import com.microsoft.msproject.model.Time;
import com.microsoft.msproject.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public List<Time> getAll() {
        return timeRepository.findAll();
    }

    public Optional<Time> getById(Long id) {
        return timeRepository.findById(id);
    }

    public Time save(Time time) {
        return timeRepository.save(time);
    }

    public void delete(Long id) {timeRepository.deleteById(id);}
}
