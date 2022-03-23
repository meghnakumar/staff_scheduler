package com.scheduler.app.model.dto;

import lombok.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCredsDTO implements Serializable{

    private Integer id;
    private String employeeNumber;
    private String emailId;
    private String loginPassword;
    private Integer roleId;
    private String departmentId;
}

