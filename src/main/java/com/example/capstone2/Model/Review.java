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


public class Review{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "user id should be not empty")
    @Column(columnDefinition = "int not null ")
    private int userId;

    @NotNull(message = "event id should be not empty")
    @Column(columnDefinition = "int not null ")
    private int eventId;

    @NotEmpty(message = "comment should be not empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String comment;

    @NotNull(message = "rating should be not empty")
    @Column(columnDefinition = "int not null")
    private double rating;









}
