package com.scheduler.app.staff.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type - Response for the Staff availability.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffAvailabilityResponse {

    private REQUEST_STATUS status;
    private boolean isEntered;
}
