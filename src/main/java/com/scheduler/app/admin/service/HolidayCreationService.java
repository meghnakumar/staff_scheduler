package com.scheduler.app.admin.service;

import com.scheduler.app.admin.model.request.HolidayCreationRequest;
import com.scheduler.app.admin.model.response.HolidayCreationResponse;

/**
 * The interface for the Holiday creation service.
 */
public interface HolidayCreationService {

    /**
     * Add new holiday to the database for all departments in the Company
     * and return the status of creation via the HolidayCreationResponse object.
     *
     * @param holidayCreationRequest the holiday creation request, with values for creating a new holiday
     * @return the holiday creation response type, with status.
     */
     HolidayCreationResponse addNewHoliday(HolidayCreationRequest holidayCreationRequest);
}
