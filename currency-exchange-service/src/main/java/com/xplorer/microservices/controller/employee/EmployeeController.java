package com.xplorer.microservices.controller.employee;

import com.xplorer.microservices.employee.entity.Employee;
import com.xplorer.microservices.employee.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/allEmployee")
    public List<Employee> findAllEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeRepository.deleteById(id);

        return "employee details removed successfully";
    }

}
