package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository,
                             EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public Department createDepartment(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new BadRequestException("Department with name '" 
                    + department.getName() + "' already exists");
        }
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(String id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> 
                        new ResourceNotFoundException("Department", "id", id));
    }

    public Department updateDepartment(String id, Department details) {
        Department department = getDepartmentById(id);

        if (!department.getName().equals(details.getName()) &&
                departmentRepository.existsByName(details.getName())) {
            throw new BadRequestException("Department already exists");
        }

        department.setName(details.getName());
        department.setDescription(details.getDescription());

        return departmentRepository.save(department);
    }

    public void deleteDepartment(String id) {
        if (!employeeRepository.findByDepartmentId(id).isEmpty()) {
            throw new BadRequestException(
                    "Cannot delete department with existing employees");
        }
        departmentRepository.deleteById(id);
    }
}
