package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "dailyshift")
public class DailyShiftPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @Getter
    @Setter
    private DepartmentPOJO department;

    @Column(name = "shift_duration", length = 50)
    @Getter
    @Setter
    private String shiftDuration;

    @Column(name = "shift_type", length = 50)
    @Getter
    @Setter
    private String shiftType;

    @Column(name = "start_time")
    @Getter
    @Setter
    private Instant startTime;

    @Column(name = "end_time")
    @Getter
    @Setter
    private Instant endTime;

}