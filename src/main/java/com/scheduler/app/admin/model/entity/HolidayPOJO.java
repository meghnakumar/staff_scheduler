package com.scheduler.app.admin.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * The type - Holiday Entity POJO.
 * Maps the fields to the 'holiday' table in the DB.
 */
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
    private Date startDate;

    @Column(name = "end_date")
    @Getter
    @Setter
    private Date endDate;

    @Column(name = "holiday_title", nullable = false, length = 50)
    @Getter
    @Setter
    private String holidayTitle;

}