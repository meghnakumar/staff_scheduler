package com.scheduler.app.utility.service;

import com.scheduler.app.supervisor.model.request.ShiftCreationRequest;
import com.scheduler.app.admin.model.response.AdminInfoResponse;
import com.scheduler.app.supervisor.model.response.ShiftCreationResponse;
import com.scheduler.app.supervisor.model.response.ShiftTimingsResponse;
import com.scheduler.app.supervisor.model.response.SupervisorInfoResponse;

public interface UtilityService {

    public AdminInfoResponse getStatistics(Boolean onload);

    public ShiftCreationResponse logNewShiftDuration(ShiftCreationRequest request);

    public ShiftTimingsResponse getShiftTimes();

    public SupervisorInfoResponse getSupervisorStats(Boolean onload, String department);
}
