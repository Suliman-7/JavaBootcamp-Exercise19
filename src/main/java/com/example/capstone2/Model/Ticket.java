package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor


public class Ticket{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "event id should be not empty")
    @Positive(message = "price should be positive")
    @Column(columnDefinition = "int not null ")
    private double price;

    @Column(columnDefinition = "datetime not null ")
    private LocalDate date;

    @NotNull(message = "event id should be not empty")
    @Positive(message = "seat number should be positive")
    @Column(columnDefinition = "int not null")
    private int seatNumber;

    @NotNull(message = "event id should be not empty")
    @Column(columnDefinition = "int not null ")
    private int eventId;

    @Column(columnDefinition = "varchar(20) not null ")
    private String seatType;

    @NotNull(message = "user id should be not empty")
    @Column(columnDefinition = "int not null ")
    private int userId;

    @Column(columnDefinition = "boolean not null ")
    private boolean returnable;

}
