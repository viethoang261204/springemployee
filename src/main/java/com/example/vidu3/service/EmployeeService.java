package com.example.vidu3.service;

import com.example.vidu3.model.Employee;
import com.example.vidu3.respository.EmployeeJdbcRepository;
import com.example.vidu3.respository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final JdbcTemplate jdbcTemplate;

    private final ModelMapper modelMapper;

    private final EmployeeJdbcRepository employeeJdbcRepository;

    public EmployeeService(EmployeeRepository employeeRepository, JdbcTemplate jdbcTemplate, EmployeeJdbcRepository employeeJdbcRepository) {
        this.employeeRepository = employeeRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.employeeJdbcRepository = employeeJdbcRepository;
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

    }

    // todo(Done) : kiểm tra trùng tên ( Check valid ở controller )
    // add Employee
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update Employee
    // todo(Done) : Xu ly cac truong hop null = mapper - Model mapper
    public Employee updateEmployee(Long id, Employee employee) throws Exception {
        if (employee == null) {
            throw new Exception("Employee is null");
        }

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new Exception("Employee not found");
        }

        Employee newEmployee = optionalEmployee.get();
        modelMapper.map(employee, newEmployee);

        return employeeRepository.save(newEmployee);
    }

    // Delete Employee
    // todo(Done) : tuning lai ham xoa
    public void deleteEmployee(Long id) throws Exception {
           if(!employeeRepository.existsById(id)) {
               throw new Exception("Employee not found");
           }
           employeeRepository.deleteById(id);
    }

    // Lấy toàn bộ danh sách nhân viên bang JPA
    // todo(Done): xu ly phan trang
    public List<Employee> getAllEmployees(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return employeeRepository.findAll();
    }

    //Lấy toàn bộ danh sách nhân viên bằng JDBC
    // todo(Done): tao 1 class xu ly JDBC rieng trong respo -> sql trong respo , su dung RowMapper
    public List<Employee> getAllEmployeesJDBC() {
        return employeeJdbcRepository.getAllEmployees();
    }

    //  Đếm số bản ghi
    public long countEmployeesJdbc() {
        return employeeJdbcRepository.countEmployees();
    }

    // todo: Thuc hien truy vna phuc tap su dung Native query
}