package utility.entity;

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
    private String DEPARTMENT_ID ="D01";
    private String DEPARTMENT_NAME = "Android";
    private Integer NUMBER_SHIFTS = 2;
    private

    @Mock
    SupervisorPOJO supervisorPOJO;

    @Test
    public void readyEmployeeHistoryPOJOTest() {

        supervisorPOJO = new SupervisorPOJO();
        departmentPOJOTest = new DepartmentPOJO();
        departmentPOJOTest.setId(DEPARTMENT_ID);
        departmentPOJOTest.setDepartmentName(DEPARTMENT_NAME);
        departmentPOJOTest.setNumberOfShifts(NUMBER_SHIFTS);
        departmentPOJOTest.setSupervisorPOJO(supervisorPOJO);

        assertEquals(DEPARTMENT_ID, departmentPOJOTest.getId());
        assertEquals(DEPARTMENT_NAME, departmentPOJOTest.getDepartmentName());
        assertEquals(NUMBER_SHIFTS, departmentPOJOTest.getNumberOfShifts());
        assertEquals(supervisorPOJO, departmentPOJOTest.getSupervisorPOJO());

    }

}