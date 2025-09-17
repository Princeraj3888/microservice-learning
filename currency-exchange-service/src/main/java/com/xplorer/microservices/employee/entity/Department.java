package com.xplorer.microservices.employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;

    private String deptName;

    @OneToMany(mappedBy = "department", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Employee> employees;

}
