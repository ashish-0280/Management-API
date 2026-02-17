package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByEmployeeId(String employeeId);

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByDepartmentId(String departmentId);

    @Query("{ $text: { $search: ?0 } }")
    List<Employee> searchByText(String text);
}
