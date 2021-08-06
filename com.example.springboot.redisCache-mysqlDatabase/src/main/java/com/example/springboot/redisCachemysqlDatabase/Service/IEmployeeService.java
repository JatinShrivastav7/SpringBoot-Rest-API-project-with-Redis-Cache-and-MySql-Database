package com.example.springboot.redisCachemysqlDatabase.Service;

import com.example.springboot.redisCachemysqlDatabase.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    public List<Employee> employeeDetails();
    public Employee employeeDetailsById(int employeeId);
    public Employee addDetails(Employee employeeAttribute);
    public Employee updateDetails(Employee employeeAttributes);
    public void deleteDetails(int employeeId);

}
