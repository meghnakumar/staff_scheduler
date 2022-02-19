package com.scheduler.app.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "EmployeeDetails")
public class EmployeeDetailsPojo {
    @Column(name="pk_employee_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    @Column(name="fk_department_id")
    private String departmentId;
    @Column(name="employee_number")
    private String employeeNumber;
    @Column(name="email_id")
    private String emailId;
    @Column(name="login_password")
    private String loginPassword;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="fk_job_role_id")
    private String jobRoleId;
    @Column(name="photo")
    private String photo;
    @Column(name="sin_number")
    private String sinNumber;
    @Column(name="date_of_joining")
    private String dateOfJoining;
    //jobType => part-time,full-time
    @Column(name="job_type")
    private int jobType;
    @Column(name="max_availability_hours")
    private double maxAvailabilityHours;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJobRoleId() {
        return jobRoleId;
    }

    public void setJobRole(String jobRole) {
        this.jobRoleId = jobRole;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSinNumber() {
        return sinNumber;
    }

    public void setSinNumber(String sinNumber) {
        this.sinNumber = sinNumber;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }

    public double getMaxAvailabilityHours() {
        return maxAvailabilityHours;
    }

    public void setMaxAvailabilityHours(double maxAvailabilityHours) {
        this.maxAvailabilityHours = maxAvailabilityHours;
    }
}
