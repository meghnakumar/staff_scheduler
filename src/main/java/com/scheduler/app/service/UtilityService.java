package com.scheduler.app.service;

import com.scheduler.app.model.response.InfoResponse;

public interface UtilityService {

    public InfoResponse getStatistics(Boolean onload);
}
