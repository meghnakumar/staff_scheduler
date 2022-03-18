package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "dailyshift")
public class DailyShiftPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @Getter
    @Setter
    private DepartmentPOJO departmentId;

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