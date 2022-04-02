package com.scheduler.app.staff.model.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * The type - Composite Key Id Class
 * Maps the composite key for the Employee Availability Entity POJO.
 */
@Embeddable
public class EmployeeAvailabilityPOJOId implements Serializable {
    private String employeeNumber;
    private Date shiftDate;

    public EmployeeAvailabilityPOJOId(String employeeNumber, Date shiftDate) {
        this.employeeNumber = employeeNumber;
        this.shiftDate = shiftDate;
    }

    public EmployeeAvailabilityPOJOId() {
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

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
