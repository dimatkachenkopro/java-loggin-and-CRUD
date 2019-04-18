package com.example.demotest.repository;

import com.example.demotest.domain.Calendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepo extends CrudRepository<Calendar, Long> {

}
