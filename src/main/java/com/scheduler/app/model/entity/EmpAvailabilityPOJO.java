package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "empavailability")
public class EmpAvailabilityPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "employee_id")
    @Getter
    @Setter
    private Integer employee_id;

    @Column(name = "employee_number")
    @Getter
    @Setter
    private String employeeNumber;

    @Column(name = "available_date")
    @Getter
    @Setter
    private Date availableDate;

    @Column(name = "available_day")
    @Getter
    @Setter
    private String availableDay;

    @Column(name = "start_time")
    @Getter
    @Setter
    private Timestamp startTime;

    @Column(name = "end_time", nullable = false)
    @Getter
    @Setter
    private Timestamp endTime;

}