package com.example.capstone2.Repository;


import com.example.capstone2.Model.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Integer> {

    Coordinator findById(int id);
}
