import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.controller.StaffController;
import com.scheduler.app.model.request.StaffAvailabilitiesRequest;
import com.scheduler.app.model.request.StaffAvailabilityRequest;
import com.scheduler.app.model.response.EmployeeDetailsResponse;
import com.scheduler.app.model.response.StaffAvailabilityResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(
        locations = "classpath:application.test.properties")
public class StaffFunctionalitiesIntegrationTests {

    @Autowired
    private StaffController staffController;

    private StaffAvailabilitiesRequest staffAvailabilitiesRequest = new StaffAvailabilitiesRequest();
    private StaffAvailabilityResponse staffAvailabilityResponse;
    private EmployeeDetailsResponse employeeDetailsResponse;


    @Test
    public void getEmployeeInformationFromDBTest(){
        employeeDetailsResponse = staffController.getEmployeeInformation("DUMMY001");
        assertNotNull(employeeDetailsResponse.getResponse());
    }
}
