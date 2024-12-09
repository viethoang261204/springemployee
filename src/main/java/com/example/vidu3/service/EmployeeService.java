package com.example.vidu3.service;

import com.example.vidu3.model.Employee;
import com.example.vidu3.respository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final JdbcTemplate jdbcTemplate;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, JdbcTemplate jdbcTemplate) {
        this.employeeRepository = employeeRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

    }

    // todo(Done) : kiểm tra trùng tên ( Check valid ở controller )
    // add Employee
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update Employee
    // todo : Xu ly cac truong hop null = mapper - Model mapper
    public Employee updateEmployee(Long id, Employee employee) throws Exception {
        if (employee == null) {
            throw new Exception("Employee is null");
        }

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new Exception("Employee not found");
        }

        Employee newEmployee = optionalEmployee.get();
        modelMapper.map(employee, newEmployee); // Ánh xạ chỉ các trường không null

        return employeeRepository.save(newEmployee);
    }

    // Delete Employee
    // todo : tuning lai ham xoa
    public void deleteEmployee(Long id) throws Exception {
            employeeRepository.deleteEmployee(employeeRepository.findById(id).get());
    }

    // Lấy toàn bộ danh sách nhân viên bang JPA
    // todo: xu ly phan trang
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //Lấy toàn bộ danh sách nhân viên bằng JDBC
    // todo: tao 1 class xu ly JDBC rieng trong respo -> sql trong respo , su dung RowMapper
    public List<Employee> getAllEmployeesJDBC() {
        String sql = "SELECT e.*, d.id AS department_id " +
                "FROM employee e " +
                "JOIN department d ON e.department_id = d.id";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setAge(rs.getInt("age"));
            employee.setSalary(rs.getInt("salary"));
            employee.setPhone(rs.getString("phone"));
            employee.setHeight(rs.getDouble("height"));
            employee.setWeight(rs.getDouble("weight"));
            employee.setDepartmentId(rs.getInt("department_id"));
            return employee;
        });
    }

    //  Đếm số bản ghi
    public long countEmployeesJdbc() {
        String sql = "SELECT COUNT(*) FROM employee";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    // todo: Thuc hien truy vna phuc tap su dung Native query
}