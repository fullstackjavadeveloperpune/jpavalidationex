package com.fullstack.controller;

import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.model.Employee;
import com.fullstack.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee) {

        log.info("Trying to signup for Employee : " + employee.getEmpFirstName());
        return new ResponseEntity<>(employeeService.signUp(employee), HttpStatus.CREATED);
    }

    @GetMapping("/signin")
    public ResponseEntity<Boolean> signIn(@RequestParam String empEmailId, @RequestParam String empPassword) {
        return new ResponseEntity<>(employeeService.signIn(empEmailId, empPassword), HttpStatus.OK);
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable int empId) {
        return new ResponseEntity<>(employeeService.findById(empId), HttpStatus.OK);
    }


    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> update(@PathVariable int empId, @Valid @RequestBody Employee employee) {
        Employee employee1 = employeeService.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee #ID Does Not Exist"));

        employee1.setEmpFirstName(employee.getEmpFirstName());
        employee1.setEmpLastName(employee.getEmpLastName());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        return new ResponseEntity<>(employeeService.update(employee1), HttpStatus.CREATED);
    }


    @PatchMapping("/changeaddress/{empId}/{empAddress}")
    public ResponseEntity<Employee> changeAddress(@PathVariable int empId, @PathVariable String empAddress) {
        Employee employee = employeeService.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee #ID Does Not Exist"));

        employee.setEmpAddress(empAddress);

        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId) {
        employeeService.deleteById(empId);
        return new ResponseEntity<>("Data Deleted Successfully", HttpStatus.OK);
    }
}
