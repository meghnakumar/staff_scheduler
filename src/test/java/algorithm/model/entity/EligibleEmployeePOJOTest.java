package algorithm.model.entity;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.algorithm.model.entity.EligibleEmployees;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.Column;
import java.sql.Time;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EligibleEmployeePOJOTest {

    private EligibleEmployees eligibleEmployees;
    final static private  Time START_TIME = Time.valueOf("12:00:00");
    final static private  Time END_TIME = Time.valueOf("18:00:00");
    final static private  String EMP_ID = "EMP001";
    final static private  int TOTAL_HOURS_WEEKLY =12;

    @Test
    public void readyEligibleEmployeePOJOTest() {


        eligibleEmployees = new EligibleEmployees(START_TIME,END_TIME,EMP_ID,TOTAL_HOURS_WEEKLY);
        eligibleEmployees.setEmployeeId(EMP_ID);
        eligibleEmployees.setAvailableStartTime(START_TIME);
        eligibleEmployees.setAvailableEndTime(END_TIME);
        eligibleEmployees.setTotalHoursLastWeek(TOTAL_HOURS_WEEKLY);

        assertEquals(EMP_ID, eligibleEmployees.getEmployeeId());
        assertEquals(START_TIME, eligibleEmployees.getAvailableStartTime());
        assertEquals(END_TIME, eligibleEmployees.getAvailableEndTime());
        assertEquals( TOTAL_HOURS_WEEKLY, eligibleEmployees.getTotalHoursLastWeek());


    }
}
