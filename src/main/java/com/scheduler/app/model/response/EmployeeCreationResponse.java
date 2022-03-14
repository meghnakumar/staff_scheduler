package com.scheduler.app.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeCreationResponse {

    private REQUEST_STATUS status;
    private boolean created;
}
