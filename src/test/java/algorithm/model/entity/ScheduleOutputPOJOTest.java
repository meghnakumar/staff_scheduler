package algorithm.model.entity;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.algorithm.model.entity.ScheduleOutputPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduleOutputPOJOTest {
   final static private Integer ID = 1;
    final static private String DEPT_ID = "D01";
    final static private Integer EMP_ID = 201;
    final static private LocalDate SHIFT_DATE = LocalDate.of(2022,2,2) ;
    final static private LocalTime START_TIME = LocalTime.of(16,00);
    final static private LocalTime END_TIME = LocalTime.of(20,00);
    final static private Integer ROLE_ID = 3;
    final static private Integer EMP_HOURS = 30;
    private ScheduleOutputPOJO scheduleOutputPOJO;

    @Test
    public void readyScheduleOutputPOJOTest() {

        scheduleOutputPOJO = new ScheduleOutputPOJO();

        scheduleOutputPOJO.setId(ID);
        scheduleOutputPOJO.setDepartmentId(DEPT_ID);
        scheduleOutputPOJO.setStartTime(START_TIME);
        scheduleOutputPOJO.setEndTime(END_TIME);
        scheduleOutputPOJO.setRoleId(ROLE_ID);
        scheduleOutputPOJO.setEmpHours(EMP_HOURS);
        scheduleOutputPOJO.setShiftDate(SHIFT_DATE);
        scheduleOutputPOJO.setEmployeeId(EMP_ID);

        assertEquals(ID, scheduleOutputPOJO.getId());
        assertEquals(EMP_HOURS,scheduleOutputPOJO.getEmpHours());
        assertEquals(DEPT_ID, scheduleOutputPOJO.getDepartmentId());
        assertEquals(START_TIME,scheduleOutputPOJO.getStartTime());
        assertEquals(END_TIME,scheduleOutputPOJO.getEndTime());
        assertEquals(SHIFT_DATE,scheduleOutputPOJO.getShiftDate());
        assertEquals(ROLE_ID,scheduleOutputPOJO.getRoleId());
        assertEquals(EMP_ID,scheduleOutputPOJO.getEmployeeId());
    }
}

