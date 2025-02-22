package com.learning.ems.controller;

import com.learning.ems.model.Employee;
import com.learning.ems.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmsController {

    private final EmployeeService service;

    public EmsController(EmployeeService service){
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return service.getAllEmployee();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = service.getEmployeeById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return service.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee eDetails){
        Employee updatedEmployee = service.updateEmployee(id, eDetails);
        return updatedEmployee != null ? ResponseEntity.ok(eDetails) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
