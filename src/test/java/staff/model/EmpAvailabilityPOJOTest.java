package staff.model;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.staff.model.entity.EmpAvailablityNewPOJO;
import com.scheduler.app.staff.model.entity.EmployeeAvailabilityPOJO;
import com.scheduler.app.utility.model.entity.DepartmentPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmpAvailabilityPOJOTest {
    final static private Integer ID = 1;
    private DepartmentPOJO departmentId;
    final static private Time START_TIME = Time.valueOf("16:00:00");
    final static private Time END_TIME = Time.valueOf("20:00:00");
    final static private Integer ROLE_ID = 2;
    final static private String SHIFT_DAY = "monday";
    final static private String DEPT_ID ="D01";
    private EmployeeAvailabilityPOJO employeeAvailabilityPOJO;

    @Test
    public void readyEmpAvailablityNewPOJOTest() {

        employeeAvailabilityPOJO = new EmployeeAvailabilityPOJO();

        employeeAvailabilityPOJO.setStartTime(START_TIME);
        employeeAvailabilityPOJO.setEndTime(END_TIME);
        employeeAvailabilityPOJO.setDepartmentId(DEPT_ID);
        employeeAvailabilityPOJO.setRoleId(ROLE_ID);
        employeeAvailabilityPOJO.setShiftDay(SHIFT_DAY);


        assertEquals(DEPT_ID, employeeAvailabilityPOJO.getDepartmentId());
        assertEquals(START_TIME, employeeAvailabilityPOJO.getStartTime());
        assertEquals(END_TIME, employeeAvailabilityPOJO.getEndTime());
        assertEquals(SHIFT_DAY, employeeAvailabilityPOJO.getShiftDay());
        assertEquals(ROLE_ID, employeeAvailabilityPOJO.getRoleId());


    }

}

