package com.scheduler.app.model.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RequiredRoleHours {
    private Integer roleId;
    private Double employeeHours;
}
