package com.scheduler.app.service;

import com.scheduler.app.model.request.HolidayCreationRequest;
import com.scheduler.app.model.response.HolidayCreationResponse;

public interface HolidayCreationService {

    public HolidayCreationResponse addNewHoliday(HolidayCreationRequest holidayCreationRequest);
}
