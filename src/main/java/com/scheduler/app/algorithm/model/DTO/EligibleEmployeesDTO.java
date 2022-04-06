package com.scheduler.app.algorithm.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;

/**
 * The type - Eligible Employees - represents one employee eligible for a shift.
 */
@Data
@AllArgsConstructor
public class EligibleEmployeesDTO implements Serializable {

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
    private Integer employeeId;

    @Column(name="total_hours_weekly")
    @Getter
    @Setter
    private Integer totalHoursLastWeek;

}