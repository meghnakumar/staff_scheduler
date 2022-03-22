package com.scheduler.app.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.entity.SchedulePOJO;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ScheduleResponse {

    private REQUEST_STATUS status;
    private boolean isScheduleAvailable;
    private Map<String, SchedulePOJO> schedule;

}
