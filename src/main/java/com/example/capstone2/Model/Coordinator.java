package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor


public class Coordinator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "username should be not empty")
    @Size(min = 5 , message = "name should be more than 4 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;

    //    @Min(value = 18 , message = "age should be at least 18")
    @NotNull(message = "age should be not empty")
    @Column(columnDefinition = "int not null")
    @Check(constraints = "age=20")
    private int age;

    @NotEmpty(message = "email should be not empty")
    @Email(message = "email should be formatted correctly")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;

    // similar to salary but per event not monthly !
    @NotNull(message = "Honorarium should be not empty")
    @Column(columnDefinition = "double not null")
    private double honorarium;

    @NotNull(message = "assigned Events should be not empty")
    @Column(columnDefinition = "int not null")
    private int assignedEvents;

    @Column(columnDefinition = "int ")
    private int eventId;
}
