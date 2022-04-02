package com.scheduler.app.algorithm.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The type - Request Object for requesting a generated Schedule
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleOutputRequest {

    private LocalDate shiftDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalTime shiftTime;

    private String departmentId;
}
