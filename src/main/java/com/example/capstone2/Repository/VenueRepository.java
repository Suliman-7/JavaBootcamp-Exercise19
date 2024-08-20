package com.example.capstone2.Repository;

import com.example.capstone2.Model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {

    Venue findById(int id);
}
