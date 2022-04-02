package com.scheduler.app.staff.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffAvailabilityResponse {
    private REQUEST_STATUS status;
    private boolean isEntered;
}
