package supervisor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.supervisor.controller.SupervisorController;
import com.scheduler.app.supervisor.model.entity.DailyShiftPOJO;
import com.scheduler.app.supervisor.model.request.ShiftDetailsRequest;
import com.scheduler.app.algorithm.model.response.ShiftDetailsResponse;
import com.scheduler.app.supervisor.service.SchedulerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SupervisorControllerUnitTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SupervisorController supervisorController;

    @Mock
    private SchedulerService schedulerService;

    private ShiftDetailsRequest shiftDetailsRequest = new ShiftDetailsRequest();


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Object[] controllers;
        mockMvc = MockMvcBuilders.standaloneSetup(supervisorController).build();
    }

    @Test
    public void testSaveShiftDetails() throws Exception {
        ShiftDetailsResponse shiftDetailsResponse = new ShiftDetailsResponse(REQUEST_STATUS.SUCCESS,true);
        createShiftDetailsRequest();
        when(schedulerService.saveShiftDetails(any())).thenReturn(shiftDetailsResponse);
        mockMvc.perform(post("/supervisor/save/shifts").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(shiftDetailsRequest))).andDo(print()).andExpect(status().isOk());
    }

//    @Test
//    public void testGetEmployees() throws Exception {
//        List<String> departmentList = new ArrayList<>();
//        departmentList.add("1");
//        ScheduleDetails sheduleDetails = new ScheduleDetails("20-08-2019",departmentList);
//        List<ScheduleDetails> output = new ArrayList<>();
//        output.add(sheduleDetails);
//        when(schedulerService.getEmployees(any())).thenReturn(output);
//        mockMvc.perform(get("/supervisor/get-schedule").param("startDate","2006-05-16")
//                .param("endDate","2020-5-6"))
//                .andDo(print()).andExpect(status().isOk());
//    }

    @Test
    public void testGetDailyShifts() throws Exception {
        DailyShiftPOJO dailyShiftPOJO = new DailyShiftPOJO();
        dailyShiftPOJO.setId(1);
        List<DailyShiftPOJO> output = new ArrayList<>();
        output.add(dailyShiftPOJO);
        when(schedulerService.getShifts(any())).thenReturn(output);
        mockMvc.perform(get("/supervisor/dailyshifts").param("shiftDate","2006-05-16"))
                                       .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeeHistory() throws Exception {
        when(schedulerService.getEmpHistory(1)).thenReturn(any());
        int employeeId = 1;
        mockMvc.perform(get("/supervisor/emphistory").param("employeeId", String.valueOf(employeeId)))
                .andDo(print()).andExpect(status().isOk());
    }


    private void createShiftDetailsRequest() {
        shiftDetailsRequest.setDepartmentId("1");
        shiftDetailsRequest.setSlotType(4);
        shiftDetailsRequest.setEndTime("16:00");
        shiftDetailsRequest.setShiftRoleHours(null);
        shiftDetailsRequest.setStartTime("12:00");
    }

}
