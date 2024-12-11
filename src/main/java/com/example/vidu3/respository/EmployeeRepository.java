package com.example.vidu3.respository;

import com.example.vidu3.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee WHERE salary > :salary AND department_id = :departmentId", nativeQuery = true)
    List<Employee> findHighSalaryEmployeesInDepartment(@Param("salary") double salary, @Param("departmentId") Long departmentId);

    @Query(value = "SELECT * FROM employee " +
            "WHERE (:name IS NULL OR name LIKE %:name%) " +
            "AND (:minSalary IS NULL OR salary >= :minSalary) " +
            "AND (:departmentId IS NULL OR department_id = :departmentId)" +
            "AND (:age is NULL OR age = :age)" +
            "AND (:height is NULL OR height = :height)" +
            "AND (:phone is NULL OR phone = :phone)" +
            "AND (:weight is NULL OR weight = :weight)", nativeQuery = true)
    List<Employee> searchEmployees(
            @Param("name") String name,
            @Param("minSalary") Double minSalary,
            @Param("departmentId") Long department_id,
            @Param("age") Integer age,
            @Param("height") Double height,
            @Param("phone") String phone,
            @Param("weight") Double weight
    );
}