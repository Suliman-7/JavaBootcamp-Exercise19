package com.example.capstone2.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "name should be not empty")
    @Size(min = 5 , message = "name should be more than 4 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String name;

    @NotNull(message = "age should be not empty")
    @Column(columnDefinition = "int not null")
    private int age;

    @NotEmpty(message = "email should be not empty")
    @Email(message = "email should be formatted correctly")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;


    @Column(columnDefinition = "double not null")
    private double balance;



}
