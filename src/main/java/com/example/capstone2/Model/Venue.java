package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor


public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should be not empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String name;

    @NotEmpty(message = "venue type should be not empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String venueType;

    @NotEmpty(message = "address should be not empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String address;

    @NotNull(message = "capacity should be not empty")
    @Column(columnDefinition = "int not null ")
    private int capacity;


}
