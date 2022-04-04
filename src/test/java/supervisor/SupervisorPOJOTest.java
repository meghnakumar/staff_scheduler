package supervisor;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import com.scheduler.app.supervisor.model.entity.SupervisorPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SupervisorPOJOTest {
    private SupervisorPOJO supervisorPOJOTest;

    @Mock
    EmpDetailPOJO empDetailPOJO;

    @Test
    public void readySupervisorPojoTest() {

        supervisorPOJOTest = new SupervisorPOJO();
        empDetailPOJO=new EmpDetailPOJO();

        supervisorPOJOTest.setId(1);
        supervisorPOJOTest.setEmployee(empDetailPOJO);
        assertEquals(Integer.valueOf(1), supervisorPOJOTest.getId());
        assertEquals(empDetailPOJO, supervisorPOJOTest.getEmployee());

    }

}