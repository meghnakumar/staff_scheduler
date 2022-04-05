package staff;

import com.scheduler.app.staff.model.entity.EmployeeAvailabilityPOJOId;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class EmployeeAvailabilityPOJOIdTest {
    final private String EMP_NUMBER = "EMP002";
    final private Date SHIFT_DATE = Date.valueOf("2022-2-2");
    private EmployeeAvailabilityPOJOId employeeAvailabilityPOJOId;

    @Test
    public void readEmployeeAvailabilityPOJOIdTest() {

        employeeAvailabilityPOJOId = new EmployeeAvailabilityPOJOId();
        employeeAvailabilityPOJOId.setEmployeeNumber(EMP_NUMBER);
        employeeAvailabilityPOJOId.setShiftDate(SHIFT_DATE);


        assertEquals(EMP_NUMBER, employeeAvailabilityPOJOId.getEmployeeNumber());
        assertEquals(SHIFT_DATE, employeeAvailabilityPOJOId.getShiftDate());



    }
}
