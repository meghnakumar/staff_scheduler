package algorithm.model.entity;

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
    final static private int ID =1;
    final static private int DAILY_SHIFT_HOUR =4;
    final static private int TOTAL_HOURS_WEEKLY =32;
    @Test
    public void readyEmployeeHistoryPOJOTest() {

        employeeHistoryPOJOTest = new EmpHistoryPOJO();
        employeeHistoryPOJOTest.setId(ID);
        employeeHistoryPOJOTest.setEmployeeId(ID);
        employeeHistoryPOJOTest.setMonday(DAILY_SHIFT_HOUR);
        employeeHistoryPOJOTest.setTuesday(DAILY_SHIFT_HOUR);
        employeeHistoryPOJOTest.setWednesday(DAILY_SHIFT_HOUR);
        employeeHistoryPOJOTest.setThursday(DAILY_SHIFT_HOUR);
        employeeHistoryPOJOTest.setFriday(DAILY_SHIFT_HOUR);
        employeeHistoryPOJOTest.setTotalHoursWeekly(TOTAL_HOURS_WEEKLY);

        assertEquals(Integer.valueOf(ID), employeeHistoryPOJOTest.getId());
        assertEquals(Integer.valueOf(ID),employeeHistoryPOJOTest.getEmployeeId());
        assertEquals(Integer.valueOf(DAILY_SHIFT_HOUR), employeeHistoryPOJOTest.getMonday());
        assertEquals(Integer.valueOf(DAILY_SHIFT_HOUR), employeeHistoryPOJOTest.getTuesday());
        assertEquals(Integer.valueOf(DAILY_SHIFT_HOUR), employeeHistoryPOJOTest.getWednesday());
        assertEquals(Integer.valueOf(DAILY_SHIFT_HOUR), employeeHistoryPOJOTest.getThursday());
        assertEquals(Integer.valueOf(DAILY_SHIFT_HOUR), employeeHistoryPOJOTest.getFriday());
        assertEquals(Integer.valueOf(TOTAL_HOURS_WEEKLY), employeeHistoryPOJOTest.getTotalHoursWeekly());

    }

}



