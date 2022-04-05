package com.scheduler.app.supervisor.model.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * The type - Required Role Hours.
 */
@Getter
@Setter
public class RequiredRoleHours {
    private Integer roleId;
    private Double employeeHours;
}
