package com.example.demotest.repository;

import com.example.demotest.domain.Person;
import com.example.demotest.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepo extends JpaRepository<Person, Long> {
    //List<Person> findBySurname(String surname);
    Person findByUsername(String username);

    Person findByRoles(Role role);
    //Application find

}
