package com.example.demo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "departments")
@Data
public class Department {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private String description;

}
