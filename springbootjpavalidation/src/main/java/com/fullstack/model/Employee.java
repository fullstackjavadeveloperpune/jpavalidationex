package com.fullstack.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;

    @Pattern(regexp="[A-Za-z]*", message="Employee First name should not contain space and special characters")
    private String empFirstName;

    @Pattern(regexp="[A-Za-z]*", message="Employee Last name should not contain space and special characters")
    private String empLastName;

    @Size(min = 2, message = "Employee Address should be atleast 2 characters")
    private String empAddress;

    private double empSalary;

    @Range(min = 1000000000, max = 9999999999L, message = "Employee Contact Number Must be 10 Digits")
    private long empContactNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;

    @Email(message = "Email ID Must be valid")
    private String empEmailId;

    @Size(min = 4, message = "Password must be 4 Characters")
    private String empPassword;


}
