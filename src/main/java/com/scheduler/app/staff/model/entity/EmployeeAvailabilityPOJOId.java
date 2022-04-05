package com.scheduler.app.staff.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * The type - Composite Key Id Class
 * Maps the composite key for the Employee Availability Entity POJO.
 */


@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EmployeeAvailabilityPOJOId implements Serializable {
    @Getter
    @Setter
    private String employeeNumber;

    @Getter
    @Setter
    private Date shiftDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeAvailabilityPOJOId that = (EmployeeAvailabilityPOJOId) o;
        return Objects.equals(getEmployeeNumber(), that.getEmployeeNumber()) && Objects.equals(getShiftDate(), that.getShiftDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeNumber(), getShiftDate());
    }
}
