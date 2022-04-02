package com.scheduler.app.utility.model.entity;

import com.scheduler.app.staff.model.entity.AssignedEmployeeDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class DepartmentRoles {

    @Getter
    @Setter
    private Integer role;

    @Getter
    @Setter
    private List<AssignedEmployeeDetail> employeeList;

}
