package com.scheduler.app.algorithm.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "scheduleoutput")
public class ScheduleOutputPOJO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "department_id", length = 50)
    @Getter
    @Setter
    private String departmentId;

    @Column(name = "employee_id")
    @Getter
    @Setter
    private Integer employeeId;

    @Column(name = "shift_date", nullable = false)
    @Getter
    @Setter
    private LocalDate shiftDate;

    @Column(name = "start_time", nullable = false)
    @Getter
    @Setter
    private LocalTime startTime;

    @Column(name = "end_time")
    @Getter
    @Setter
    private LocalTime endTime;

    @Column(name = "role_id")
    @Getter
    @Setter
    private Integer roleId;

    @Column(name = "emp_hours")
    @Getter
    @Setter
    private Integer empHours;

}