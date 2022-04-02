package com.scheduler.app.supervisor.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupervisorInfoResponse {

    private REQUEST_STATUS status;
    private Map<String, String> upcomingHolidays;
    private Long deptEmpCount;
}
