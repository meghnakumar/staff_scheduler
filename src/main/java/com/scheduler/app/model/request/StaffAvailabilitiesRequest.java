package com.scheduler.app.model.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class StaffAvailabilitiesRequest {
    private List<StaffAvailabilityRequest> staffAvailabilityRequest;
}
