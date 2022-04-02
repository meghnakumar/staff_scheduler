package com.scheduler.app.staff.model.request;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeAvailabilityExistsRequest {

    String employeeNumber;

    List<String> dates;
}
