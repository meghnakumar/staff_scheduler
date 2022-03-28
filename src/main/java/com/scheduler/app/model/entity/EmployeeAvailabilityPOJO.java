package com.scheduler.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalTime;

/**
 * The type - Employee Availability Entity POJO.
 * Maps the fields to the 'empavailability' table in the DB.
 */
@Entity
@Table(name = "empavailablitynew")
public class EmployeeAvailabilityPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    @JsonIgnore
    private Integer id;

    @Column(name = "employee_id")
    @Getter
    @Setter
    private Integer employeeId;

    @Column(name = "role_id")
    @Getter
    @Setter
    private Integer roleId;

    @Column(name = "shiftdate")
    @Getter
    @Setter
    private Date shiftDate;

    @Column(name = "shiftday")
    @Getter
    @Setter
    private String shiftDay;

    @Column(name = "department_id")
    @Getter
    @Setter
    private String departmentId;

    @Column(name = "starttime")
    @Getter
    @Setter
    private LocalTime startTime;

    @Column(name = "endtime")
    @Getter
    @Setter
    private LocalTime endTime;

}