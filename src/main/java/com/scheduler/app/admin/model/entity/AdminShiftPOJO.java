package com.scheduler.app.admin.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalTime;

/**
 * The type - Admin Shift Entity POJO.
 * Maps the fields to the 'admin' table in the DB.
 */
@Entity
@Table(name = "admin")
public class AdminShiftPOJO {

    @Id
    @Column(name = "shift_creation_date", nullable = false)
    @Getter
    @Setter
    private Date shiftCreationDate;

    @Column(name = "slot_type")
    @Getter
    @Setter
    private Integer slotType;

    @Column(name = "shift1_start_time")
    @Getter
    @Setter
    private LocalTime shift1StartTime;

    @Column(name = "shift2_start_time")
    @Getter
    @Setter
    private LocalTime shift2StartTime;

    @Column(name = "shift3_start_time")
    @Getter
    @Setter
    private LocalTime shift3StartTime;

    @Column(name = "shift4_start_time")
    @Getter
    @Setter
    private LocalTime shift4StartTime;

    @Column(name = "shift5_start_time")
    @Getter
    @Setter
    private LocalTime shift5StartTime;

    @Column(name = "shift6_start_time")
    @Getter
    @Setter
    private LocalTime shift6StartTime;

}