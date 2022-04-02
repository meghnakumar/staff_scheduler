package com.scheduler.app.supervisor.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type - Request object to Store new Shift Duration in the DB.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftCreationRequest {

    private Integer shiftDuration;
}
