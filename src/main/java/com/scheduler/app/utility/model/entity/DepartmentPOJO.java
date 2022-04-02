package com.scheduler.app.utility.model.entity;

import com.scheduler.app.supervisor.model.entity.SupervisorPOJO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * The type - Department Entity POJO.
 * Maps the fields to the 'department' table in the DB.
 */
@Entity
@Table(name = "department")
public class DepartmentPOJO {
    @Id
    @Column(name = "department_id", nullable = false, length = 50)
    @Getter
    @Setter
    private String id;

    @Column(name = "department_name", nullable = false, length = 50)
    @Getter
    @Setter
    private String departmentName;

    @Column(name = "number_of_shifts")
    @Getter
    @Setter
    private Integer numberOfShifts;

    //Has a Foreign Key - "supervisor_id" in the 'supervisor' table. [SupervisorPOJO]
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supervisor_id")
    @Getter
    @Setter
    private SupervisorPOJO supervisorPOJO;



}