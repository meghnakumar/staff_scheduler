package com.scheduler.app.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.model.entity.HolidayPOJO;
import com.scheduler.app.model.repo.HolidayRepo;
import com.scheduler.app.model.request.HolidayCreationRequest;
import com.scheduler.app.model.response.HolidayCreationResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;

@Service
public class HolidayCreationServiceImpl implements HolidayCreationService{


    @Autowired
    HolidayRepo holidayRepo;

    @Override
    public HolidayCreationResponse addNewHoliday(HolidayCreationRequest holidayCreationRequest) {

        HolidayPOJO holiday = new HolidayPOJO();
        HolidayCreationResponse holidayCreationResponse = new HolidayCreationResponse(REQUEST_STATUS.FAILED,false);
        boolean isValid = validDates(holidayCreationRequest.getStartDate(), holidayCreationRequest.getEndDate());

        if(Strings.isNotBlank(holidayCreationRequest.getHolidayTitle()) && isValid){

            holiday.setHolidayTitle(holidayCreationRequest.getHolidayTitle());
            holiday.setStartDate(holidayCreationRequest.getStartDate());
            holiday.setEndDate(holidayCreationRequest.getEndDate());

            HolidayPOJO response = holidayRepo.saveAndFlush(holiday);

            if(response != null) {
                holidayCreationResponse.setCreated(true);
                holidayCreationResponse.setStatus(REQUEST_STATUS.SUCCESS);
                return holidayCreationResponse;
            }
        }

        return holidayCreationResponse;
    }

    public boolean validDates(Date startDate, Date endDate) {

        return (startDate != null && endDate != null && (endDate.equals(startDate) || endDate.after(startDate)));
    }
}
