package com.scheduler.app.supervisor.model.entity;

import com.scheduler.app.utility.model.entity.DepartmentPOJO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


/**
 * The type - Daily Shift Entity POJO.
 * Maps the fields to the 'dailyshift' table in the DB.
 */
@Entity
@Table(name = "dailyshift")
public class DailyShiftPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    //Has a Foreign Key - "department_id" in the 'department' table. [DepartmentPOJO]
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @Getter
    @Setter
    private DepartmentPOJO department;

    @Column(name = "shift_type", length = 50)
    @Getter
    @Setter
    private String shiftType;

    @Column(name = "start_time")
    @Getter
    @Setter
    private Time startTime;

    @Column(name = "end_time")
    @Getter
    @Setter
    private Time endTime;

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