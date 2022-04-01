package com.scheduler.app.model.request;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeAvailabilityExistsRequest {

    String employeeNumber;

    List<String> dates;
}
