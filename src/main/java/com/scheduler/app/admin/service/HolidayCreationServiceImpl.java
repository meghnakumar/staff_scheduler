package com.scheduler.app.admin.service;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.model.entity.HolidayPOJO;
import com.scheduler.app.admin.model.repo.HolidayRepository;
import com.scheduler.app.admin.model.request.HolidayCreationRequest;
import com.scheduler.app.admin.model.response.HolidayCreationResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;

/**
 * Holiday Creation Service.
 * This Service helps in the creation & registration of a Holiday in the System.
 * All the details for the new Holiday are received form the front-end via the Admin Flow.
 */
@Service
public class HolidayCreationServiceImpl implements HolidayCreationService{

    /**
     * Auto-wired Component : JPA Repo to the 'holiday' table.
     */
    @Autowired
    HolidayRepository holidayRepository;


    /**
     * Add new holiday to the database for all departments in the Company
     * and return the status of creation via the HolidayCreationResponse object.
     *
     * @param holidayCreationRequest the holiday creation request, with values for creating a new holiday
     * @return the holiday creation response type, with status.
     */
    @Override
    public HolidayCreationResponse addNewHoliday(HolidayCreationRequest holidayCreationRequest) {

        //Default to failure response.
        HolidayCreationResponse holidayCreationResponse = new HolidayCreationResponse(REQUEST_STATUS.FAILED,false);
        //Check the validity of the Holiday Time span
        boolean isValid = validDates(holidayCreationRequest.getStartDate(), holidayCreationRequest.getEndDate());

        //if the Dates are valid and the Holiday has a Title value,
        //Create a new HolidayPOJO object and save to DB
        if(Strings.isNotBlank(holidayCreationRequest.getHolidayTitle()) && isValid){
            HolidayPOJO holiday = new HolidayPOJO();
            holiday.setHolidayTitle(holidayCreationRequest.getHolidayTitle());
            holiday.setStartDate(holidayCreationRequest.getStartDate());
            holiday.setEndDate(holidayCreationRequest.getEndDate());

            //saving the new Holiday to the DB Table.
            HolidayPOJO response = holidayRepository.saveAndFlush(holiday);

            //If the holiday ws saved successfully, Respond with the success values.
            if(response != null) {
                holidayCreationResponse.setCreated(true);
                holidayCreationResponse.setStatus(REQUEST_STATUS.SUCCESS);
            }

        }

        //Return the response for holiday creation.
        return holidayCreationResponse;
    }


    /**
     * Checks the validity for the Holiday's time period, i.e.
     * the End date for the Holiday period should be same or after the Start date.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the boolean whether the Holiday period is valid.
     */
    public boolean validDates(Date startDate, Date endDate) {

        return (startDate != null && endDate != null && (endDate.equals(startDate) || endDate.after(startDate)));
    }
}
