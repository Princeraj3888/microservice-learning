package com.xplorer.microservices.employee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String city;
}
