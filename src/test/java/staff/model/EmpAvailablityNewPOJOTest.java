package staff.model;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.staff.model.entity.EmpAvailablityNewPOJO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.supervisor.model.entity.SupervisorPOJO;
import com.scheduler.app.utility.model.entity.DepartmentPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmpAvailablityNewPOJOTest {
    private Integer ID = 1;
    private DepartmentPOJO departmentId;
    private Date SHIFT_DATE = Date.valueOf("2022-2-2");
    private Time START_TIME = Time.valueOf("16:00:00");
    private Time END_TIME = Time.valueOf("20:00:00");
    private Integer ROLE_ID = 2;
    private String SHIFT_DAY = "monday";
    private Integer EMPLOYEE_ID = 1;
    private EmpAvailablityNewPOJO empAvailablityNewPOJOTest;

    @Test
    public void readyEmpAvailablityNewPOJOTest() {

        empAvailablityNewPOJOTest = new EmpAvailablityNewPOJO();
        departmentId = new DepartmentPOJO();
        empAvailablityNewPOJOTest.setId(ID);
        empAvailablityNewPOJOTest.setEmployeeId(EMPLOYEE_ID);
        empAvailablityNewPOJOTest.setDepartmentId(departmentId);
        empAvailablityNewPOJOTest.setShiftdate(SHIFT_DATE);
        empAvailablityNewPOJOTest.setStartTime(START_TIME);
        empAvailablityNewPOJOTest.setEndTime(END_TIME);
        empAvailablityNewPOJOTest.setShiftDay(SHIFT_DAY);
        empAvailablityNewPOJOTest.setRoleId(ROLE_ID);

        assertEquals(ID, empAvailablityNewPOJOTest.getId());
        assertEquals(EMPLOYEE_ID, empAvailablityNewPOJOTest.getId());
        assertEquals(departmentId, empAvailablityNewPOJOTest.getDepartmentId());
        assertEquals(SHIFT_DATE, empAvailablityNewPOJOTest.getShiftdate());
        assertEquals(SHIFT_DAY, empAvailablityNewPOJOTest.getShiftDay());
        assertEquals(START_TIME, empAvailablityNewPOJOTest.getStartTime());
        assertEquals(END_TIME, empAvailablityNewPOJOTest.getEndTime());
        assertEquals(ROLE_ID, empAvailablityNewPOJOTest.getRoleId());


    }

}

