package com.scheduler.app.service;

import com.scheduler.app.model.request.ShiftCreationRequest;
import com.scheduler.app.model.response.AdminInfoResponse;
import com.scheduler.app.model.response.ShiftCreationResponse;
import com.scheduler.app.model.response.ShiftTimingsResponse;
import com.scheduler.app.model.response.SupervisorInfoResponse;

public interface UtilityService {

    public AdminInfoResponse getStatistics(Boolean onload);

    public ShiftCreationResponse logNewShiftDuration(ShiftCreationRequest request);

    public ShiftTimingsResponse getShiftTimes();

    public SupervisorInfoResponse getSupervisorStats(Boolean onload, String department);
}
