package com.example.vidu3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "employee")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Please enter your name.")
    private String name;

    @NotNull(message = "Please enter your age.")
    private Integer age;

    @NotNull(message = "Please enter your salary.")
    private Integer salary;

    @NotBlank(message = "Please enter your phone.")
    private String phone;

    @NotNull(message = "Please enter your height.")
    private Double height;

    @NotNull(message = "Please enter your weight.")
    private Double weight;

    @NotNull(message = "Please enter your departmentId.")
    private Integer departmentId;

}

