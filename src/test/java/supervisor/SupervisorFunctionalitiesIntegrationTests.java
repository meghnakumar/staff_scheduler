package supervisor;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.supervisor.controller.SupervisorController;
import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;
import com.scheduler.app.supervisor.model.request.RequiredRoleHours;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.supervisor.model.response.ShiftDetailsResponse;
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

    private SupervisorInfoResponse supervisorInfoResponse;

    private String START_TIME = "16:00";
    final private String END_TIME = "18:00";
    final private String DEPARTMENT_ID = "D01";
    final private Integer SLOT_TYPE = 4;
    final private String SHIFT_DATE = "2020-09-09";
    final private Double EMP_HOURS = 20.9;
    final private String date = "2020-02-04";
    final private String SUCCESS = "success";

    @Test
    public void saveShiftDetailsToDBTest(){
        shiftDetailsRequest = new ShiftDetailsRequest();
        RequiredRoleHours hours = new RequiredRoleHours();
        hours.setEmployeeHours(EMP_HOURS);
        Integer ROLE_ID = 1;
        hours.setRoleId(ROLE_ID);
        List<RequiredRoleHours> hourList = new ArrayList<>();
        hourList.add(hours);
        shiftDetailsRequest.setShiftRoleHours(hourList);
        shiftDetailsRequest.setSlotType(SLOT_TYPE);
        shiftDetailsRequest.setStartTime(START_TIME);
        shiftDetailsRequest.setDepartmentId(DEPARTMENT_ID);
        shiftDetailsRequest.setEndTime(END_TIME);
        shiftDetailsRequest.setShiftDate(SHIFT_DATE);
        shiftDetailsResponse = supervisorController.inputShiftDetails(shiftDetailsRequest);
        assertEquals(REQUEST_STATUS.SUCCESS,shiftDetailsResponse.getStatus());
    }

    @Test
    public void getSupervisorInfoFromDBTest(){
        supervisorInfoResponse = supervisorController.getStatistic(true, "DepartmentId");
        assertNotNull(supervisorInfoResponse.getDeptEmpCount());
    }
}
