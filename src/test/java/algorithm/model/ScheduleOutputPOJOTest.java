package algorithm.model;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.algorithm.model.entity.ScheduleOutputPOJO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.supervisor.model.entity.SupervisorPOJO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduleOutputPOJOTest {
    private Integer ID = 1;
    private String DEPT_ID = "D01";
    private Integer EMP_ID = 201;
    private LocalDate SHIFT_DATE ;
    private LocalTime START_TIME;
    private LocalTime END_TIME;
    private Integer ROLE_ID;
    private Integer EMP_HOURS;
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

