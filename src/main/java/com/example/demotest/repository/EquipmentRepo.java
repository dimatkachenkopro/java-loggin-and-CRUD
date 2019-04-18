package com.example.demotest.repository;

import com.example.demotest.domain.Equipment;
import com.example.demotest.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EquipmentRepo extends JpaRepository<Equipment, Long> {
    //    Equipment findBySprzet(String nazwa_sprzetu);
    Equipment findBySerialNumber(String serialNumber);

    ArrayList<Equipment> findAllByUser(Person user);


}
