package com.scheduler.app.staff.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeAvailabilityExistsResponse {

    REQUEST_STATUS status;

    boolean modified;

    List<String> dates;
}
