package com.scheduler.app.model.request;

import com.scheduler.app.model.entity.DepartmentPOJO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeCreationRequest {

    private Integer employeeID;
    private String employeeNumber;
    private String emailId;
    private String loginPassword;
    private String firstName;
    private String lastName;
    private Integer phoneNumber;
    private String roleId;
    private byte[] photo;
    private String sinNumber;
    private String departmentId;
    private LocalDate dateOfJoining;
    private Integer jobType;
    private Double maxAvailabilityHours;
}
