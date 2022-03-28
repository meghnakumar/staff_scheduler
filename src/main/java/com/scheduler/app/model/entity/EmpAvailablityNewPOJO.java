package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "empavailablitynew")
public class EmpAvailablityNewPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @Getter
    @Setter
    private DepartmentPOJO departmentId;

    @Column(name = "shiftdate")
    @Getter
    @Setter
    private Date shiftdate;

    @Column(name = "starttime")
    @Getter
    @Setter
    private Time startTime;

    @Column(name = "endtime")
    @Getter
    @Setter
    private Time endTime;

    @Column(name = "role_id")
    @Getter
    @Setter
    private Integer roleId;

    @Column(name = "shiftday")
    @Getter
    @Setter
    private String shiftDay;

    @Column(name = "employee_id")
    @Getter
    @Setter
    private Integer employeeId;



}
