package com.scheduler.app.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "holiday")
public class HolidayPOJO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holiday_id", nullable = false)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "start_date", nullable = false)
    @Getter
    @Setter
    private LocalDate startDate;

    @Column(name = "end_date")
    @Getter
    @Setter
    private LocalDate endDate;

    @Column(name = "holiday_title", nullable = false, length = 50)
    @Getter
    @Setter
    private String holidayTitle;

}