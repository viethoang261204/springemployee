package com.example.vidu3.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmployeePageDTO {
    private List<Employee> employees;
    private long totalrecords;
    private int totalpages;

    public EmployeePageDTO(List<Employee> employees, long totalrecords, int totalpages) {
        this.employees = employees;
        this.totalrecords = totalrecords;
        this.totalpages = totalpages;
    }
}
