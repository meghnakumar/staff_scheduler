package com.scheduler.app.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;

import java.util.List;
import java.util.Map;

import com.scheduler.app.model.entity.ScheduleOutputPOJO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponse {

    private REQUEST_STATUS status;
    private boolean isScheduleAvailable;
    private Map<String, List<ScheduleOutputPOJO>> schedule;

}
