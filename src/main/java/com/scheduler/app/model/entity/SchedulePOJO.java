package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalTime;

/**
 * The type - Schedule POJO.
 * Maps the fields to the 'outputschema' table in the DB.
 */
@Entity
@Table(name = "outputschema", indexes = {@Index(name = "department_id", columnList = "department_id")})
public class SchedulePOJO {

    //Table has a Composite Key handled by ScheduleCompositeId type.
    @EmbeddedId
    private ScheduleCompositeId id;

    //Has a Foreign Key - "department_id" in the 'department' table. [DepartmentPOJO]
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @Getter
    @Setter
    private DepartmentPOJO department;

    @Column(name = "primary_emp_no")
    @Getter
    @Setter
    private Integer primaryEmpNo;

    @Column(name = "primary_emp_startime")
    @Getter
    @Setter
    private LocalTime primaryEmpStartime;

    @Column(name = "primary_emp_endtime")
    @Getter
    @Setter
    private LocalTime primaryEmpEndtime;

    @Column(name = "secondary_emp_no")
    @Getter
    @Setter
    private Integer secondaryEmpNo;

    @Column(name = "secondary_emp_start_time")
    @Getter
    @Setter
    private LocalTime secondaryEmpStartTime;

    @Column(name = "secondary_emp_end_time")
    @Getter
    @Setter
    private LocalTime secondaryEmpEndTime;
}