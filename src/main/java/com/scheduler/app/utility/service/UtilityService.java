package com.scheduler.app.utility.service;

import com.scheduler.app.supervisor.model.request.ShiftCreationRequest;
import com.scheduler.app.admin.model.response.AdminInfoResponse;
import com.scheduler.app.supervisor.model.response.ShiftCreationResponse;
import com.scheduler.app.supervisor.model.response.ShiftTimingsResponse;
import com.scheduler.app.supervisor.model.response.SupervisorInfoResponse;

/**
 * The interface for the Utility service.
 */
public interface UtilityService {

    /**
     * Gets statistics about the company employees and departments to be display on the Admin Homepage.
     *
     * @param onload the onload boolean value signifying page load.
     * @return the statistics - the response values to be displayed in the UI.
     */
    public AdminInfoResponse getStatistics(Boolean onload);

    /**
     * Logs new shift duration for all departments in the company.
     *
     * @param request the request for new Shift Duration
     * @return the shift creation response with status.
     */
    public ShiftCreationResponse logNewShiftDuration(ShiftCreationRequest request);

    /**
     * Gets shift times for the shifts set by the Admin, for the most recent date in the DB.
     *
     * @return the shift times via the ShiftTimingsResponse objects.
     */
    public ShiftTimingsResponse getShiftTimes();

    /**
     * Gets statistics about the department level employees and departments to be display on the Supervisor Homepage.
     *
     * @param onload     the onload boolean value signifying page load.
     * @param department the department - Which Department the Supervisor presides.
     * @return the supervisor stats response via the SupervisorInfoResponse object.
     */
    public SupervisorInfoResponse getSupervisorStats(Boolean onload, String department);
}
