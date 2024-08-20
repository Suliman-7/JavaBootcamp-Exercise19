package com.example.capstone2.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should be not empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String name;

    @NotEmpty(message = "description should be not empty")
    @Column(columnDefinition = "varchar(100) not null ")
    private String description;

    @NotNull(message = "number of bookings should be not empty")
    @Column(columnDefinition = "double not null ")
    private double numOfBookings;

    @Column(columnDefinition = "boolean not null ")
    @AssertFalse
    private boolean isFull;

    @NotEmpty(message = "Event type should be not empty")
    @Column(columnDefinition = "varchar(20) not null ")
    @Pattern(regexp = "^(Match|Theatrical|Concert)$")
    private String eventType;

    @Column(columnDefinition = "datetime not null ")
    private LocalDate eventDate;

    @NotEmpty(message = "city should be not empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String city;

    @NotNull(message = "organiser id should be not empty")
    @Column(columnDefinition = "int not null ")
    private int organiserId;

    @NotNull(message = "venue id should be not empty")
    @Column(columnDefinition = "int not null ")
    private int venueId;

    @Column(columnDefinition = "boolean not null")
    private boolean returnable;


    @Column(columnDefinition = "double not null ")
    private double discountpercentage = 0 ;

    @Column(columnDefinition = "double not null ")
    private double rate = 0 ;






}
