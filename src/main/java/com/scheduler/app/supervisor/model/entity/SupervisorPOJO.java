package com.scheduler.app.supervisor.model.entity;

import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * The type - Supervisor Entity POJO.
 * Maps the fields to the 'supervisor' table in the DB.
 */
@Entity
@Table(name = "supervisor")
public class SupervisorPOJO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supervisor_id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    //Has a Foreign Key - "employee_id" in the 'empdetails' table. [EmpDetailPOJO]
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter
    @Setter
    @JoinColumn(name = "employee_id")
    private EmpDetailPOJO employee;

}