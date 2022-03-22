package com.scheduler.app.model.request;

import com.scheduler.app.model.entity.DepartmentPOJO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
