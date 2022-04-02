package com.scheduler.app.admin.model.dto;

import lombok.*;
import java.io.Serializable;

/**
 * The type - Employee Creds DTO.
 * Saves a Sub-section of the details from the EmpDetailPOJO Entity.
 */
@Data
@AllArgsConstructor
public class EmployeeCredsDTO implements Serializable{

    private Integer id;
    private String employeeNumber;
    private String emailId;
    private String loginPassword;
    private Integer roleId;
    private String departmentId;
}

