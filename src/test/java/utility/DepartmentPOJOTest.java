package utility;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import com.scheduler.app.supervisor.model.entity.SupervisorPOJO;
import com.scheduler.app.utility.model.entity.DepartmentPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentPOJOTest {

    private DepartmentPOJO departmentPOJOTest;

    @Mock
    SupervisorPOJO supervisorPOJO;

    @Test
    public void readyEmployeeHistoryPOJOTest() {

        supervisorPOJO = new SupervisorPOJO();
        departmentPOJOTest = new DepartmentPOJO();
        departmentPOJOTest.setId("D01");
        departmentPOJOTest.setDepartmentName("Android");
        departmentPOJOTest.setNumberOfShifts(2);
        departmentPOJOTest.setSupervisorPOJO(supervisorPOJO);


        assertEquals("D01", departmentPOJOTest.getId());
        assertEquals("Android", departmentPOJOTest.getDepartmentName());
        assertEquals(Integer.valueOf(2), departmentPOJOTest.getNumberOfShifts());
        assertEquals(supervisorPOJO, departmentPOJOTest.getSupervisorPOJO());

    }

}