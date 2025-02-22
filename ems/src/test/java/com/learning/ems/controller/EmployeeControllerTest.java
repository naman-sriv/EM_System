package com.learning.ems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.ems.model.Employee;
import com.learning.ems.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll(); // Clean database before each test
        employee = new Employee(null, "John Doe", "john.doe@example.com", "Engineering");
        employeeRepository.save(employee);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        Employee newEmployee = new Employee(null, "Jane Doe", "jane.doe@example.com", "HR");

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee updatedEmployee = new Employee(null, "Updated Name", "updated@example.com", "IT");

        mockMvc.perform(put("/api/employees/" + employee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/api/employees/" + employee.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/employees/" + employee.getId()))
                .andExpect(status().isNotFound());
    }
}
