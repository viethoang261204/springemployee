package com.example.vidu3.controller;


import com.example.vidu3.model.Employee;
import com.example.vidu3.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/employee")
public class EmployeeController {

    final EmployeeService employeeService;

    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String index() {
        return "day la viet hoang ne";
    }

    @PostMapping("/add")
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) throws Exception {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") long id) throws Exception {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/list")
    public Page<Employee> getAllEmployee() {
        return employeeService.getAllEmployees(3,5);
    }

    @GetMapping("/listjdbc")
    public List<Employee> getAllEmployeeJdbc() {
        return employeeService.getAllEmployeesJDBC();
    }

    @GetMapping("/count")
    public long countEmployees() {
        return employeeService.countEmployeesJdbc();
    }
    // todo : Them validate . 2 rang buoc DB + Code
    // todo: Them ham search theo nhieu dieu kien khac nhau , co phan trang ,tim hieu ve Caching ( bo nho dem ) , Spring cache
}
