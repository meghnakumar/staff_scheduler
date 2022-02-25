package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor_id")
    @Getter
    @Setter
    private SupervisorPOJO supervisorPOJO;

    @OneToMany(mappedBy = "department")
    @Getter
    @Setter
    private Set<JobrolePOJO> jobroles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "department")
    @Getter
    @Setter
    private Set<EmpDetailPOJO> empDetailPOJOS = new LinkedHashSet<>();

    @OneToMany(mappedBy = "department")
    @Getter
    @Setter
    private Set<DailyShiftPOJO> dailyshifts = new LinkedHashSet<>();



}