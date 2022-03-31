import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.controller.UtilityController;
import com.scheduler.app.model.response.ShiftTimingsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(
        locations = "classpath:application.test.properties")
public class UtilityFunctionIntegrationTests {

    @Autowired
    private UtilityController utilityController;

    private ShiftTimingsResponse shiftTimingsResponse;

    @Test
    public void getShiftTimesFromDBTest(){
        shiftTimingsResponse = utilityController.getShiftTimes();
        assertNotNull(shiftTimingsResponse);
    }
}
