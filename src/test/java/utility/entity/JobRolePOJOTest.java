package utility.entity;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.supervisor.model.entity.SupervisorPOJO;
import com.scheduler.app.utility.model.entity.DepartmentPOJO;
import com.scheduler.app.utility.model.entity.JobrolePOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JobRolePOJOTest {
    private JobrolePOJO jobRolePOJOTest;
    private final String ID = "1";
    private final String JOB_ROLE_TITLE = "supervisor";
    private final Integer TOTAL_SHIFT_WEEK= 1;
    @Mock
    DepartmentPOJO departmentPOJO;

    @Test
    public void readyJobRolePOJOTest() {


        departmentPOJO = new DepartmentPOJO();
        jobRolePOJOTest = new JobrolePOJO();
        jobRolePOJOTest.setId(ID);
        jobRolePOJOTest.setRoleTitle(JOB_ROLE_TITLE);
        jobRolePOJOTest.setDepartment(departmentPOJO);
        jobRolePOJOTest.setTotalShiftsWeek(TOTAL_SHIFT_WEEK);

        assertEquals(ID, jobRolePOJOTest.getId());
        assertEquals(JOB_ROLE_TITLE, jobRolePOJOTest.getRoleTitle());
        assertEquals(TOTAL_SHIFT_WEEK, jobRolePOJOTest.getTotalShiftsWeek());
        assertEquals(departmentPOJO, jobRolePOJOTest.getDepartment());

    }


}