# Employee Management System

## 📌 Overview

This is a Spring Boot CRUD application that provides RESTful APIs to manage employees. The application supports:
Creating new employees
Fetching employee details
Updating existing employees
Deleting employees

## 🚀 Features

Spring Boot with Spring Data JPA for database interactions
H2 in-memory database for development and testing
Spring Boot REST API with CRUD endpoints
Unit & Integration Testing using JUnit & Mockito
Postman Collection for API testing

## 🏗️ Technologies Used

Java 17
Spring Boot 3.4.3
Spring Data JPA
H2 Database (for testing)
Maven (for dependency management)
Lombok (to reduce boilerplate code)
JUnit & Mockito (for testing)

📌 API Endpoints

To retrieve all employees, send a GET request to /api/employees.
To retrieve a specific employee by their ID, send a GET request to /api/employees/{id}.
To create a new employee, send a POST request to /api/employees with the employee details in the request body.
To update an existing employee's details, send a PUT request to /api/employees/{id} with the updated information.
To delete an employee, send a DELETE request to /api/employees/{id}.
