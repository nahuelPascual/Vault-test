package com.vault.test.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@SequenceGenerator(name = "employeesSequence", sequenceName = "SEQ_EMPLOYEES", allocationSize = 1)
@Table(name = "EMPLOYEES")
@NamedQueries({
        @NamedQuery(name = Employee.GET_EMPLOYEES_QUERY, query = Employee.GET_EMPLOYEES_QUERY_ST),
        @NamedQuery(name = Employee.GET_SALARIES_QUERY, query = Employee.GET_SALARIES_QUERY_ST)
})

public class Employee implements Serializable {

    private static final int SIZE_FIRST_NAME = 20;
    private static final int SIZE_LAST_NAME = 25;
    private static final int SIZE_EMAIL = 25;
    private static final int SIZE_PHONE_NUMBER = 20;

    public static final String GET_EMPLOYEES_QUERY = "getEmployees";
    public static final String GET_EMPLOYEES_QUERY_ST = "SELECT e FROM Employee e " +
            "WHERE (:jobId IS NULL OR :jobId = e.job.jobId)" +
            "AND (:managerId IS NULL OR :managerId = e.manager.employeeId)" +
            "AND (:lastName IS NULL OR :lastName = e.lastName)";

    public static final String GET_SALARIES_QUERY = "getSalaries";
    public static final String GET_SALARIES_QUERY_ST = "SELECT AVG(e.salary) FROM Employee e " +
            "JOIN e.department d " +
            "JOIN d.location l " +
            "WHERE l.locationId = :locationId";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeesSequence")
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
    private Date hireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_ID")
    private Job job;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "COMMISSION_PCT")
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
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
