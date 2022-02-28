package com.scheduler.app.service;

import com.scheduler.app.model.request.HolidayCreationRequest;
import com.scheduler.app.model.response.HolidayCreationResponse;
import org.springframework.stereotype.Service;

@Service
public class HolidayCreationServiceImpl implements HolidayCreationService{

    @Override
    public HolidayCreationResponse addNewHoliday(HolidayCreationRequest holidayCreationRequest) {
        return null;
    }
}
