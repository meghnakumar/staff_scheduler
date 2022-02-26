package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "jobrole")
public class JobrolePOJO {
    @Id
    @Column(name = "role_id", nullable = false, length = 50)
    @Getter
    @Setter
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
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