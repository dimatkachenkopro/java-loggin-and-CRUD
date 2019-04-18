package com.example.demotest.repository;

import com.example.demotest.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepo extends JpaRepository<Position, Long> {
    Position findByPosition(String Position);
}
