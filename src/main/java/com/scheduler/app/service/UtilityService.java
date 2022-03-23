package com.scheduler.app.service;

import com.scheduler.app.model.request.ShiftCreationRequest;
import com.scheduler.app.model.response.InfoResponse;
import com.scheduler.app.model.response.ShiftCreationResponse;

public interface UtilityService {

    public InfoResponse getStatistics(Boolean onload);

    public ShiftCreationResponse logNewShiftDuration(ShiftCreationRequest request);
}
