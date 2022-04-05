package com.scheduler.app.staff.model.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * The type - Request object for Staff availabilities request.
 */
@Getter
@Setter
public class StaffAvailabilitiesRequest {

    private List<StaffAvailabilityRequest> staffAvailabilityRequestList;
}
