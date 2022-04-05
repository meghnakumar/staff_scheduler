package staff.model;

import com.scheduler.app.staff.model.entity.EmployeeAvailabilityPOJOId;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class EmployeeAvailabilityPOJOIdTest {
    final private String EMP_NUMBER = "EMP002";
    final private Date SHIFT_DATE = Date.valueOf("2022-2-2");
    private EmployeeAvailabilityPOJOId employeeAvailabilityPOJOId;
    private EmployeeAvailabilityPOJOId employeeAvailabilityPOJOId1;

    @Mock
    Object o;

    @Test
    public void readEmployeeAvailabilityPOJOIdTest() {

        employeeAvailabilityPOJOId = new EmployeeAvailabilityPOJOId();
        employeeAvailabilityPOJOId1 = new EmployeeAvailabilityPOJOId(EMP_NUMBER,SHIFT_DATE);

        employeeAvailabilityPOJOId.setEmployeeNumber(EMP_NUMBER);
        employeeAvailabilityPOJOId.setShiftDate(SHIFT_DATE);
        employeeAvailabilityPOJOId.hashCode();
        employeeAvailabilityPOJOId.equals(o);

        assertEquals(EMP_NUMBER, employeeAvailabilityPOJOId.getEmployeeNumber());
        assertEquals(SHIFT_DATE, employeeAvailabilityPOJOId.getShiftDate());



    }
}
