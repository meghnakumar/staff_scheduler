package com.scheduler.app.supervisor.model.response;

import com.scheduler.app.constants.REQUEST_STATUS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftTimingsResponse {

    private REQUEST_STATUS status;
    private int slotType;
    private List<String> shiftTimes;

}
