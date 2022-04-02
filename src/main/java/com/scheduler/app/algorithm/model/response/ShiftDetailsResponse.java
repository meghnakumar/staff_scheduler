package com.scheduler.app.algorithm.model.response;

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
