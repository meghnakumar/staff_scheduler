package com.scheduler.app.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDTO {

    private Integer id;

    private String employeeNumber;

    private String emailId;

    private String loginPassword;

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
