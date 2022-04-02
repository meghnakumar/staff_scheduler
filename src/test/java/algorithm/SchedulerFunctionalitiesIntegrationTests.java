package algorithm;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.algorithm.controller.ScheduleController;
import com.scheduler.app.algorithm.model.request.ScheduleOutputRequest;
import com.scheduler.app.algorithm.model.response.ScheduleOutputResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(
        locations = "classpath:application.test.properties")
public class SchedulerFunctionalitiesIntegrationTests {

    @Autowired
    private ScheduleController scheduleController;

    private ScheduleOutputRequest scheduleOutputRequest;
    private ScheduleOutputResponse scheduleOutputResponse;

    @Test
    public void getScheduleByShiftFromDB(){
        scheduleOutputRequest = new ScheduleOutputRequest();
        scheduleOutputRequest.setDepartmentId("DepartmentId");
        scheduleOutputRequest.setShiftDate(LocalDate.ofEpochDay(2020-11-02));
        scheduleOutputRequest.setShiftTime(LocalTime.MAX);
        scheduleOutputResponse = scheduleController.getScheduleByShift(scheduleOutputRequest);
        assertNotNull(scheduleOutputResponse.getSchedule());
    }
}
