package com.scheduler.app.algorithm.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


/**
 * The type - Employee History Entity POJO.
 * Maps the fields to the 'emphistory' table in the DB.
 */
@Entity
@Table(name = "emphistory")
public class EmpHistoryPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "employee_id")
    @Getter
    @Setter
    private Integer employeeId;

    @Column(name = "monday")
    @Getter
    @Setter
    private String monday;

    @Column(name = "tuesday")
    @Getter
    @Setter
    private Date tuesday;

    @Column(name = "wednesday")
    @Getter
    @Setter
    private String wednesday;

    @Column(name = "thursday")
    @Getter
    @Setter
    private Integer thursday;

    @Column(name = "friday")
    @Getter
    @Setter
    private Integer friday;


    @Column(name = "total_hours_weekly")
    @Getter
    @Setter
    private Integer totalHoursWeekly;
}
