package com.microsoft.msproject.repository;

import com.microsoft.msproject.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time,Long> {
}
