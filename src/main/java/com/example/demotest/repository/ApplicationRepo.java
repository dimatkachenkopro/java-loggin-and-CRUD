package com.example.demotest.repository;

import com.example.demotest.domain.Application;
import com.example.demotest.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ApplicationRepo extends JpaRepository<Application, Long> {
    Application findByUserId(Person user);

    ArrayList<Application> findAllByUserId(Person user);
}
