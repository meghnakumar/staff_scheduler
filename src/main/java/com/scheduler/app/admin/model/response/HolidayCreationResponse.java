package com.scheduler.app.admin.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type - Response object for Holiday creation.
 */
@Getter
@Setter
@AllArgsConstructor
public class HolidayCreationResponse {

    private REQUEST_STATUS status;
    private boolean created;
}
