package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "empavailability")
public class EmpAvailabilityPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @Getter
    @Setter
    private EmpDetailPOJO empdetails;

    @Column(name = "available_date")
    @Getter
    @Setter
    private LocalDate availableDate;

    @Column(name = "available_day")
    @Getter
    @Setter
    private LocalDate availableDay;

    @Column(name = "start_time")
    @Getter
    @Setter
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    @Getter
    @Setter
    private Instant endTime;


}