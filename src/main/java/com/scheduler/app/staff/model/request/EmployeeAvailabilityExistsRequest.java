package com.scheduler.app.staff.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The type  - Request object for Employee availability exists check.
 */

@Getter
@Setter
public class EmployeeAvailabilityExistsRequest {


    String employeeNumber;
    List<String> dates;
}
