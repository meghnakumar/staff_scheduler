package com.scheduler.app.supervisor.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShiftDetailsResponse {
    private REQUEST_STATUS status;
    private Boolean isSaved;
}
