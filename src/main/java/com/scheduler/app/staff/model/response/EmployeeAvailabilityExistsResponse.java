package com.scheduler.app.staff.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type - Response object for Employee availability exists check.
 */

@Getter
@Setter
public class EmployeeAvailabilityExistsResponse {

    REQUEST_STATUS status;
    boolean modified;
    List<String> dates;
}
