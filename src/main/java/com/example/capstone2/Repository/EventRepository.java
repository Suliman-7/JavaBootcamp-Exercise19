package com.example.capstone2.Repository;

import com.example.capstone2.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findById(int id);

    @Query("select e from Event e where e.eventDate>=?1 and e.eventDate<=?2")
    List<Event> findByEventDateBetween(LocalDate start, LocalDate end);

    @Query("select e from Event e where e.eventType=?1")
    List<Event> findByEventType(String type);
}
