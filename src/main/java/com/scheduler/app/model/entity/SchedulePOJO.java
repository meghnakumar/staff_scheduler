package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "outputschema", indexes = {@Index(name = "department_id", columnList = "department_id")})
public class SchedulePOJO {

    @EmbeddedId
    private ScheduleCompositeId id;

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