package com.vault.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Employee {

    private static final int SIZE_FIRST_NAME = 20;
    private static final int SIZE_LAST_NAME = 25;
    private static final int SIZE_EMAIL = 25;
    private static final int SIZE_PHONE_NUMBER = 20;
    private static final int SIZE_JOB_ID = 10;

    @Id
    private Long employeeId;

    @Column(name = "FIRST_NAME")
    @Size(max = SIZE_FIRST_NAME)
    private String firstName;

    @Column(name = "LAST_NAME")
    @Size(max = SIZE_LAST_NAME)
    private String lastName;

    @Column(name = "EMAIL")
    @Size(max = SIZE_EMAIL)
    private String email;

    @Column
    @Size(max = SIZE_PHONE_NUMBER)
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HIRE_DATE")
    private String hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_ID")
    private Job job;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "COMISSION_PST")
    private Double comissionPct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getComissionPct() {
        return comissionPct;
    }

    public void setComissionPct(Double comissionPct) {
        this.comissionPct = comissionPct;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
