package com.example.vidu3.respository;

import com.example.vidu3.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Employee> getAllEmployees() {
        String sql = "SELECT e.*, d.id AS department_id " +
                "FROM employee e " +
                "JOIN department d ON e.department_id = d.id";

        List<Employee> employees = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));

        for (Employee employee : employees) {
            employee.setDepartmentId(employee.getDepartmentId());
        }

        return employees;
    }

    public long countEmployees() {
        String sql = "SELECT COUNT(*) FROM employee";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
