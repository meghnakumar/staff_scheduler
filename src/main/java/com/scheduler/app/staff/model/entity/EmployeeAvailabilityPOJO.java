package com.scheduler.app.staff.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

/**
 * The type - Employee Availability Entity POJO.
 * Maps the fields to the 'empavailability' table in the DB.
 */
@Entity
@Table(name = "empavailability")
public class EmployeeAvailabilityPOJO {

    @EmbeddedId
    @Getter
    @Setter
    private EmployeeAvailabilityPOJOId id;

    @Column(name = "role_id")
    @Getter
    @Setter
    private Integer roleId;


    @Column(name = "shift_day")
    @Getter
    @Setter
    private String shiftDay;

    @Column(name = "department_id")
    @Getter
    @Setter
    private String departmentId;

    @Column(name = "start_time")
    @Getter
    @Setter
    private LocalTime startTime;

    @Column(name = "end_time")
    @Getter
    @Setter
    private LocalTime endTime;

}