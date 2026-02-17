package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "employees")
@Data
public class Employee {

    @Id
    private String id;

    @Indexed(unique = true)
    private String employeeId;

    @Indexed
    private String name;

    private String departmentId;
}
