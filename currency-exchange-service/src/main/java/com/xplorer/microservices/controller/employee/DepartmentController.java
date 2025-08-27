package com.xplorer.microservices.controller.employee;

import com.xplorer.microservices.employee.entity.Department;
import com.xplorer.microservices.employee.entity.Employee;
import com.xplorer.microservices.employee.repository.DepartmentRepository;
import com.xplorer.microservices.employee.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/allDepartment")
    public List<Department> findAllDepartment(){
        return departmentRepository.findAll();
    }

    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department){
        return departmentRepository.save(department);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id){
        departmentRepository.deleteById(id);

        return "department details removed successfully";
    }
}
