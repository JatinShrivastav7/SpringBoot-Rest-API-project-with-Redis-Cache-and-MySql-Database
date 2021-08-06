package com.example.springboot.redisCachemysqlDatabase.Dao;

import com.example.springboot.redisCachemysqlDatabase.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
