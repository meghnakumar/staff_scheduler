package algorithm;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.algorithm.model.entity.EligibleEmployees;
import com.scheduler.app.algorithm.model.entity.EmpHistoryPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Time;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeHistoryPOJOTest {
    private EmpHistoryPOJO employeeHistoryPOJOTest;

    @Test
    public void readyEmployeeHistoryPOJOTest() {

        employeeHistoryPOJOTest = new EmpHistoryPOJO();
        employeeHistoryPOJOTest.setId(1);
        employeeHistoryPOJOTest.setEmployeeId(1);
        employeeHistoryPOJOTest.setMonday(4);
        employeeHistoryPOJOTest.setTuesday(4);
        employeeHistoryPOJOTest.setWednesday(4);
        employeeHistoryPOJOTest.setThursday(4);
        employeeHistoryPOJOTest.setFriday(4);
        employeeHistoryPOJOTest.setTotalHoursWeekly(32);

        assertEquals(Integer.valueOf(1), employeeHistoryPOJOTest.getId());
        assertEquals(Integer.valueOf(4), employeeHistoryPOJOTest.getMonday());
        assertEquals(Integer.valueOf(4), employeeHistoryPOJOTest.getTuesday());
        assertEquals(Integer.valueOf(4), employeeHistoryPOJOTest.getWednesday());
        assertEquals(Integer.valueOf(4), employeeHistoryPOJOTest.getThursday());
        assertEquals(Integer.valueOf(4), employeeHistoryPOJOTest.getFriday());
        assertEquals(Integer.valueOf(32), employeeHistoryPOJOTest.getTotalHoursWeekly());

    }

}



