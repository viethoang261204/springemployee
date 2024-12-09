package com.example.vidu3.respository;

import com.example.vidu3.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
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

    public long countEmployees() {
        String sql = "SELECT COUNT(*) FROM employee";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
