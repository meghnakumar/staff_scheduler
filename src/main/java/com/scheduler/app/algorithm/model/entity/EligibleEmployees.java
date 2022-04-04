package com.scheduler.app.algorithm.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Time;




@Data
public class EligibleEmployees implements Serializable {

    @Column(name ="starttime")
    @Getter
    @Setter
    private Time availableStartTime;

    @Column(name="endtime")
    @Getter
    @Setter
    private Time availableEndTime;

    @Column(name="employee_id")
    @Getter
    @Setter
    private String employeeId;

    @Column(name="total_hours_weekly")
    @Getter
    @Setter
    private int totalHoursLastWeek;

    public EligibleEmployees(Time availableStartTime, Time availableEndTime, String employeeId, int totalHoursLastWeek){

    this.availableStartTime=availableStartTime;
    this.availableEndTime=availableEndTime;
    this.totalHoursLastWeek=totalHoursLastWeek;
    this.employeeId=employeeId;
    }
}
