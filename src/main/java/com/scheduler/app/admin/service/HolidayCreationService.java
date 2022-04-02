package com.scheduler.app.admin.service;

import com.scheduler.app.admin.model.request.HolidayCreationRequest;
import com.scheduler.app.admin.model.response.HolidayCreationResponse;

public interface HolidayCreationService {

    public HolidayCreationResponse addNewHoliday(HolidayCreationRequest holidayCreationRequest);
}
