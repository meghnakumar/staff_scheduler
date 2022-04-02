package com.scheduler.app.utility.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * The type - Jobrole Entity POJO.
 * Maps the fields to the 'jobrole' table in the DB.
 */
@Entity
@Table(name = "jobrole")
public class JobrolePOJO {
    @Id
    @Column(name = "role_id", nullable = false, length = 50)
    @Getter
    @Setter
    private String id;

    //Has a Foreign Key - "department_id" in the 'department' table. [DepartmentPOJO]
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @Getter
    @Setter
    private DepartmentPOJO department;

    @Column(name = "role_title", nullable = false, length = 50)
    @Getter
    @Setter
    private String roleTitle;

    @Column(name = "total_shifts_week")
    @Getter
    @Setter
    private Integer totalShiftsWeek;

}