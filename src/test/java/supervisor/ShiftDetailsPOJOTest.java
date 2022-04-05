package supervisor;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.supervisor.model.entity.ShiftDetailsPOJO;
import com.scheduler.app.supervisor.model.entity.SupervisorPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ShiftDetailsPOJOTest {
    private Integer ID = 1;
    private String DEPT_ID = "D01";
    private Integer SHIFT_TYPE = 1;
    private LocalTime START_TIME;
    private LocalTime END_TIME;
    private Date SHIFT_DATE;
    private Integer ROLE_ID;
    private Double EMP_HOUR;

    ShiftDetailsPOJO shiftDetailsPOJO;
    @Test
    public void readyShiftDetailPOJOTest() {
        shiftDetailsPOJO = new ShiftDetailsPOJO();
        shiftDetailsPOJO.setId(ID);
        shiftDetailsPOJO.setDepartmentId(DEPT_ID);
        shiftDetailsPOJO.setShiftType(SHIFT_TYPE);
        shiftDetailsPOJO.setEmployeeHours(EMP_HOUR);
        shiftDetailsPOJO.setStartTime(START_TIME);
        shiftDetailsPOJO.setEndTime(END_TIME);
        shiftDetailsPOJO.setRoleId(ROLE_ID);
        shiftDetailsPOJO.setShiftDate(SHIFT_DATE);
        shiftDetailsPOJO.setEmployeeHours(EMP_HOUR);

        assertEquals(ID, shiftDetailsPOJO.getId());
        assertEquals(DEPT_ID, shiftDetailsPOJO.getDepartmentId());
        assertEquals(SHIFT_TYPE,shiftDetailsPOJO.getShiftType());
        assertEquals(START_TIME,shiftDetailsPOJO.getStartTime());
        assertEquals(END_TIME,shiftDetailsPOJO.getEndTime());
        assertEquals(SHIFT_TYPE,shiftDetailsPOJO.getShiftType());
        assertEquals(ROLE_ID,shiftDetailsPOJO.getRoleId());
        assertEquals(EMP_HOUR,shiftDetailsPOJO.getEmployeeHours());
    }
}
