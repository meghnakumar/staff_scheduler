package com.scheduler.app.model.entity;

import com.scheduler.app.model.dto.EmployeeCredsDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

//Does not Require maintaining order of Columns, but it's a Good Practice.
// DOES  MANDATE THAT THE DTO HAVE SAME FIELD NAMES AS POJO
@NamedNativeQuery(name = "Fetch_Credentials",
        query = "select e.employee_id, e.employee_number, e.email_id, e.login_password, e.job_type FROM empdetails e WHERE e.employee_id = ?1 ")
@SqlResultSetMapping(name = "Fetch_Credentials", classes = @ConstructorResult(targetClass = EmployeeCredsDTO.class,
        columns = {@ColumnResult(name = "id"),@ColumnResult(name = "employeeNumber"),@ColumnResult(name = "emailId"), @ColumnResult(name = "loginPassword"),@ColumnResult(name = "jobType")}))

@Entity(name = "empdetails")
@Table(name = "empdetails")
public class EmpDetailPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    @Getter
    @Setter
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @Getter
    @Setter
    private DepartmentPOJO department;

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
    private Integer phoneNumber;

    @Column(name = "role_id", nullable = false, length = 50)
    @Getter
    @Setter
    private String roleId;

    @Column(name = "photo")
    @Getter
    @Setter
    private byte[] photo;

    @Column(name = "sin_number", length = 50)
    @Getter
    @Setter
    private String sinNumber;

    @Column(name = "date_of_joining", nullable = false)
    @Getter
    @Setter
    private LocalDate dateOfJoining;

    @Column(name = "job_type")
    @Getter
    @Setter
    private Integer jobType;

    @Column(name = "max_availability_hours")
    @Getter
    @Setter
    private Double maxAvailabilityHours;


}