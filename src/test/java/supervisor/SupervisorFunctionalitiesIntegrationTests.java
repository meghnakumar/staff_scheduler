package supervisor;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.supervisor.controller.SupervisorController;
import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;
import com.scheduler.app.supervisor.model.request.RequiredRoleHours;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.algorithm.model.response.ShiftDetailsResponse;
import com.scheduler.app.supervisor.model.response.SupervisorInfoResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(
        locations = "classpath:application.test.properties")
public class SupervisorFunctionalitiesIntegrationTests {

    @Autowired
    private SupervisorController supervisorController;

    private ShiftDetailsResponse shiftDetailsResponse;
    private ShiftDetailsRequest shiftDetailsRequest;

    private List<DailyShiftPOJO> dailyShiftPOJOS;
    private SupervisorInfoResponse supervisorInfoResponse;

    @Test
    public void saveShiftDetailsToDBTest(){
        shiftDetailsRequest = new ShiftDetailsRequest();
        RequiredRoleHours hours = new RequiredRoleHours();
        hours.setEmployeeHours(20.9);
        hours.setRoleId(1);
        List<RequiredRoleHours> hourList = new ArrayList<>();
        hourList.add(hours);
        shiftDetailsRequest.setShiftRoleHours(hourList);
        shiftDetailsRequest.setSlotType(4);
        shiftDetailsRequest.setStartTime("16:00");
        shiftDetailsRequest.setDepartmentId("1");
        shiftDetailsRequest.setEndTime("18:00");
        shiftDetailsRequest.setShiftDate("2020-09-09");
        shiftDetailsResponse = supervisorController.shiftDetails(shiftDetailsRequest);
        assertEquals(REQUEST_STATUS.SUCCESS,shiftDetailsResponse.getStatus());
    }

    @Test
    public void getShiftDetailsFromDBTest(){
        String date = "2020-02-04";
        Date dateValue = Date.valueOf(date);
        dailyShiftPOJOS = supervisorController.getShifts(dateValue);
        assertNotNull(dailyShiftPOJOS);
    }

    @Test
    public void getEmployeeHistoryFromDBTest(){
        String employeeHistory = supervisorController.getEmpHistory(1);
        assertEquals("success",employeeHistory);
    }

    @Test
    public void getSupervisorInfoFromDBTest(){
        supervisorInfoResponse = supervisorController.getStatistic(true, "DepartmentId");
        assertNotNull(supervisorInfoResponse.getDeptEmpCount());
    }
}
