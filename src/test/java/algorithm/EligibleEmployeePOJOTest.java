package algorithm;

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



    @Test
    public void readyEligibleEmployeePOJOTest() {


        final EligibleEmployees mockEligibleEmployees = Mockito.mock(EligibleEmployees.class, Mockito.RETURNS_DEEP_STUBS);
/*     Mockito.when(mockEligibleEmployees.getEmployeeId()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(mockEligibleEmployees).setEmployeeId("Emp001");

        Mockito.when(mockEligibleEmployees.getAvailableStartTime()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(mockEligibleEmployees).setAvailableStartTime(Time.valueOf("12:00:00"));

        Mockito.when((mockEligibleEmployees.getAvailableEndTime())).thenCallRealMethod();
        Mockito.doCallRealMethod().when(mockEligibleEmployees).setAvailableEndTime(Time.valueOf("8:00:00"));

        Mockito.when((mockEligibleEmployees.getTotalHoursLastWeek())).thenCallRealMethod();
        Mockito.doCallRealMethod().when(mockEligibleEmployees).setTotalHoursLastWeek(9);*/
        eligibleEmployees = new EligibleEmployees(Time.valueOf("12:00:00"),Time.valueOf("18:00:00"),"EMP001",12);
        eligibleEmployees.setEmployeeId("EMP001");
        eligibleEmployees.setAvailableStartTime(Time.valueOf("12:00:00"));
        eligibleEmployees.setAvailableEndTime(Time.valueOf("18:00:00"));
        eligibleEmployees.setTotalHoursLastWeek(12);

        assertEquals("EMP001", eligibleEmployees.getEmployeeId());
        assertEquals(Time.valueOf("12:00:00"), eligibleEmployees.getAvailableStartTime());
        assertEquals(Time.valueOf("18:00:00"), eligibleEmployees.getAvailableEndTime());
        assertEquals( 12, eligibleEmployees.getTotalHoursLastWeek());


    }
}
