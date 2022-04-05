package com.scheduler.app.staff.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.model.dto.EmployeeDetailDTO;
import lombok.*;
import com.scheduler.app.staff.model.dto.EmployeeDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type  - Response object for Employee details.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsResponse {

    private REQUEST_STATUS status;
    private EmployeeDetailDTO response;
}

