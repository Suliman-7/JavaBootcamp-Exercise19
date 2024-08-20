package com.example.capstone2.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor


public class Organiser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should be not empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String name;

    @Email()
    @NotEmpty(message = "email should be not empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String email;

    @NotEmpty(message = "address should be not empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private String address;

    @Column(columnDefinition = "datetime not null ")
    private LocalDate foundDate;






}
