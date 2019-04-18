package com.example.demotest.repository;

import com.example.demotest.domain.Equipment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SprzetSortRepo extends PagingAndSortingRepository<Equipment, Long> {
    @Query("SELECT DISTINCT e.model FROM Equipment e")
    List<String> findAllDepartments(Sort sort);
}
