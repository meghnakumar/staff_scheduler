package com.scheduler.app.supervisor.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;


/**
 * The type - Shift Details Entity POJO.
 * Maps the fields to the 'dailyshift' table in the DB.
 */
@Entity
@Table(name = "dailyshift")
public class ShiftDetailsPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "department_id")
    @Getter
    @Setter
    private String departmentId;

    @Column(name = "shift_type", length = 50)
    @Getter
    @Setter
    private Integer shiftType;

    @Column(name = "start_time")
    @Getter
    @Setter
    private LocalTime startTime;

    @Column(name = "end_time")
    @Getter
    @Setter
    private LocalTime endTime;

    @Column(name = "shift_date")
    @Getter
    @Setter
    private Date shiftDate;

    @Column(name = "role_id")
    @Getter
    @Setter
    private Integer roleId;

    @Column(name = "employee_hours")
    @Getter
    @Setter
    private Double employeeHours;


}