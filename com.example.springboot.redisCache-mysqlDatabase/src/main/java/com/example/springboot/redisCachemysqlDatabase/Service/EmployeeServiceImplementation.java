package com.example.springboot.redisCachemysqlDatabase.Service;

import com.example.springboot.redisCachemysqlDatabase.Dao.EmployeeRepository;
import com.example.springboot.redisCachemysqlDatabase.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImplementation implements IEmployeeService {


    @Autowired
    private EmployeeRepository daoEmployeeDetails;
    @Override
    public List<Employee> employeeDetails() {
        return this.daoEmployeeDetails.findAll();

    }

    @Override
    public Employee employeeDetailsById(int employeeId) {
        Optional<Employee> emp = daoEmployeeDetails.findById(employeeId);

        if(emp!=null){
            return emp.get();
        }
        else{
            return null;
        }
    }

    @Override
    public Employee addDetails(Employee employeeAttribute) {
        return daoEmployeeDetails.save(employeeAttribute);
    }

    @Override
    public Employee updateDetails(Employee employeeAttributes) {
        daoEmployeeDetails.save(employeeAttributes);
        return employeeAttributes;
    }

    @Override
    public void deleteDetails(int employeeId) {
        daoEmployeeDetails.deleteById(employeeId);
    }
}
