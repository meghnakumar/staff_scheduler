package com.scheduler.app.admin.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The type - Request Object for Employee Creation.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreationRequest {

    private Integer employeeID;
    private String employeeNumber;
    private String emailId;
    private String loginPassword = "XYZ";
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private Integer roleId;
    private byte[] photo;
    private String sinNumber;
    private String departmentId;
    private LocalDate dateOfJoining;
    private Integer jobType;
    private Double maxAvailabilityHours;
}
