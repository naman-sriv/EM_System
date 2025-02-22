package com.learning.ems.service;

import com.learning.ems.model.*;
import com.learning.ems.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo){
        this.repo = repo;
    }

    public List<Employee> getAllEmployee() {
        return repo.findAll();
    }

    public Employee getEmployeeById(Long id){
        return repo.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee){
        return repo.save(employee);
    }

    public Employee updateEmployee(Long id, Employee eDetail){

        Optional<Employee> optionalEmployee = repo.findById(id);
        if(optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            employee.setName(eDetail.getName());
            employee.setEmail(eDetail.getEmail());
            employee.setDepartment(eDetail.getDepartment());
            return repo.save(employee);
        }

        return null;
    }

    public void deleteEmployee(Long id){
        repo.deleteById(id);
    }
}
