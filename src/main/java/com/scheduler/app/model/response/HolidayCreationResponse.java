package com.scheduler.app.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolidayCreationResponse {

    private REQUEST_STATUS status;
    private boolean isCreated;
}
