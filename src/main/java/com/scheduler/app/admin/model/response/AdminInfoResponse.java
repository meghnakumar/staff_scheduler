package com.scheduler.app.admin.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminInfoResponse {

    private Long empCount;
    private Long deptCount;
    private Map<String, String> deptInfo;
    private Map<String, String> upcomingHolidays;
    private REQUEST_STATUS status;
}
