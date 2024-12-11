package com.example.vidu3.controller;


import com.example.vidu3.model.Employee;
import com.example.vidu3.model.EmployeePageDTO;
import com.example.vidu3.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Delete Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }


    @GetMapping("/list")
    public EmployeePageDTO getAllEmployee(@RequestParam("page") int page, @RequestParam("size") int size) {
        return employeeService.getAllEmployees(page,size);
    }

    @GetMapping("/listjdbc")
    public List<Employee> getAllEmployeeJdbc() {
        return employeeService.getAllEmployeesJDBC();
    }

    @GetMapping("/count")
    public long countEmployees() {
        return employeeService.countEmployeesJdbc();
    }

    @GetMapping("salary")
    public ResponseEntity<List<Employee>> getHighSalaryEmployees(
            @RequestParam double salary,
            @RequestParam Long departmentId) {
        List<Employee> employees = employeeService.findHighSalaryEmployees(salary, departmentId);
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Double height,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Double weight) {
        List<Employee> employees = employeeService.searchEmployees(name, minSalary, departmentId,age,height,phone,weight);
        return ResponseEntity.ok(employees);
    }

    // todo(Done) : Them validate . 2 rang buoc DB + Code
    // todo: Them ham search theo nhieu dieu kien khac nhau , co phan trang ,tim hieu ve Caching ( bo nho dem ) , Spring cache
}
