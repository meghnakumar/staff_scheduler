package com.scheduler.app.algorithm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.sql.Time;


@Data
public class EligibleEmployees implements Serializable {

    @Column(name ="starttime")
    @Getter
    @Setter
    public Time availableStartTime;

    @Column(name="endtime")
    @Getter
    @Setter
    public Time availableEndTime;

    @Column(name="employee_id")
    @Getter
    @Setter
    public String employeeId;

    @Column(name="total_hours_weekly")
    @Getter
    @Setter
    public int totalHoursLastWeek;

    public EligibleEmployees(Time availableStartTime, Time availableEndTime, String employeeId, int totalHoursLastWeek){

    this.availableStartTime=availableStartTime;
    this.availableEndTime=availableEndTime;
    this.totalHoursLastWeek=totalHoursLastWeek;
    this.employeeId=employeeId;
    }
}
