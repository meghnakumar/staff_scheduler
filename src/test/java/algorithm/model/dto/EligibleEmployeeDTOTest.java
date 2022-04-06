package algorithm.model.dto;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.admin.model.dto.EmployeeCredsDTO;
import com.scheduler.app.algorithm.model.DTO.EligibleEmployeesDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Time;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class EligibleEmployeeDTOTest {

    private static final Time availableStartTime = Time.valueOf("16:00:00");
    private static final Time availableEndTime = Time.valueOf("20:00:00");
    private static final Integer employee_id = 1;
    private static final Integer totalHoursWeekly = 20;
    EligibleEmployeesDTO eligibleEmployeesDTO ;

    @Test
    public void readEligibleEmployeeDTOTest() {
        eligibleEmployeesDTO=new EligibleEmployeesDTO(availableStartTime,availableEndTime,employee_id,totalHoursWeekly);
        eligibleEmployeesDTO.setEmployeeId(employee_id);
        eligibleEmployeesDTO.setAvailableEndTime(availableEndTime);
        eligibleEmployeesDTO.setAvailableStartTime(availableStartTime);
        eligibleEmployeesDTO.setTotalHoursLastWeek(totalHoursWeekly);


        assertEquals(availableStartTime, eligibleEmployeesDTO.getAvailableStartTime());
        assertEquals(availableEndTime, eligibleEmployeesDTO.getAvailableEndTime());
        assertEquals(employee_id, eligibleEmployeesDTO.getEmployeeId());
        assertEquals(totalHoursWeekly,eligibleEmployeesDTO.getTotalHoursLastWeek());


    }
}
