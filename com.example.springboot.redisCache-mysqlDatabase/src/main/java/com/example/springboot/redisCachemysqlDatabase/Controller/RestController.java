package com.example.springboot.redisCachemysqlDatabase.Controller;

import com.example.springboot.redisCachemysqlDatabase.Entity.Employee;
import com.example.springboot.redisCachemysqlDatabase.Service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/employee")
public class RestController {

    private  final Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private IEmployeeService employeeService;

    //Get method to get all employee details

    @GetMapping("/employeeDetails")
    public ResponseEntity<List<Employee>> getEmployeeDetails()
    {
        logger.debug(" >> RestController : /employeeDetails : ");
        List<Employee> employeesList=this.employeeService.employeeDetails();
        if(employeesList.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.of(Optional.of(employeesList));

    }

    //Get method to get Employee details by id

    @GetMapping("/employeeDetails/{employeeId}")
    @Cacheable(key = "#employeeId",value = "employee")
    public Employee getEmployeeDetailsById(@PathVariable String employeeId)
    {
        logger.debug(" >> RestController : /employeeDetails/{} call : ",employeeId);
        return employeeService.employeeDetailsById(Integer.parseInt(employeeId));
    }

    //Post method to add new employee details

    @PostMapping("/addDetails")
    public ResponseEntity<Employee> addEmployeeDetails(@RequestBody Employee employeeAttributeBody)
    {
        logger.debug(" >> RestController : /addDetails : ",employeeAttributeBody.toString());
        Employee empAttributes=null;

        try {
            empAttributes = this.employeeService.addDetails(employeeAttributeBody);
            System.out.println(empAttributes);
            return ResponseEntity.of(Optional.of(empAttributes));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //Put method to update existing employee details

    @PutMapping("/updateDetails")
    @CachePut(key = "#employeeAttributeBody.employeeId",value = "employee")
    public Employee updateEmployeeDetails(@RequestBody Employee employeeAttributeBody)
    {
        logger.debug(" >> RestController : /updateDetails : ",employeeAttributeBody.toString());
        Employee empAttributes= employeeService.updateDetails(employeeAttributeBody);
            return empAttributes;
    }

    //Delete method to delete employee details

    @DeleteMapping("/deleteDetails/{employeeId}")
    @CacheEvict(allEntries = false,value = "employee",key = "#employeeId")

    public ResponseEntity<HttpStatus> deleteEmployeeDetails(@PathVariable String employeeId)
    {
        logger.debug(" >> RestController : /deleteDetails : ",employeeId);
        try {
            this.employeeService.deleteDetails(Integer.parseInt(employeeId));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
