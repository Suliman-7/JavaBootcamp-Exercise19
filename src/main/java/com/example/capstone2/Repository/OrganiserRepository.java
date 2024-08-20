package com.example.capstone2.Repository;

import com.example.capstone2.Model.Organiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganiserRepository extends JpaRepository<Organiser, Integer> {

    Organiser findById(int id);
}
