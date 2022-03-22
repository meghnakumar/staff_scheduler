package com.scheduler.app.model.entity;

import com.scheduler.app.model.dto.EmployeeCredsDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "empdetails")
@Table(name = "empdetails")
public class EmpDetailPOJO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "employee_number")
    @Getter
    @Setter
    private String employeeNumber;

    @Column(name = "email_id", nullable = false, length = 100)
    @Getter
    @Setter
    private String emailId;

    @Column(name = "login_password", nullable = false, length = 50)
    @Getter
    @Setter
    private String loginPassword;

    @Column(name = "first_name", nullable = false, length = 100)
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name", length = 100)
    @Getter
    @Setter
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    @Getter
    @Setter
    private Long phoneNumber;

    @Column(name = "role_id", nullable = false, length = 50)
    @Getter
    @Setter
    private Integer roleId;

    @Column(name = "photo")
    @Getter
    @Setter
    private byte[] photo;

    @Column(name = "sin_number", length = 50)
    @Getter
    @Setter
    private String sinNumber;

    @Column(name = "department_id")
    @Getter
    @Setter
    private String departmentId;

    @Column(name = "date_of_joining", nullable = false)
    @Getter
    @Setter
    private LocalDate dateOfJoining;

    @Column(name = "job_type")
    @Getter
    @Setter
    private Integer jobType;

    @Column(name = "max_available_hours")
    @Getter
    @Setter
    private Double maxAvailabilityHours;


}