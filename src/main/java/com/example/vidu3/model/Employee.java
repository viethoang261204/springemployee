package com.example.vidu3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Please enter your age.")
    private Integer age;

    @NotBlank(message = "Please enter your salary.")
    private Integer salary;

    @NotBlank(message = "Please enter your phone.")
    private String phone;

    @NotBlank(message = "Please enter your height.")
    private Double height;

    @NotBlank(message = "Please enter your weight.")
    private Double weight;

    @NotBlank(message = "Please enter your departmentId.")
    private Integer departmentId;

}

