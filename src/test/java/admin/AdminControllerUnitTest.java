package admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.controller.AdminController;
import com.scheduler.app.admin.model.request.EmployeeCreationRequest;
import com.scheduler.app.admin.model.request.HolidayCreationRequest;
import com.scheduler.app.supervisor.model.request.ShiftCreationRequest;
import com.scheduler.app.admin.model.response.EmployeeCreationResponse;
import com.scheduler.app.admin.model.response.HolidayCreationResponse;
import com.scheduler.app.admin.model.response.AdminInfoResponse;
import com.scheduler.app.supervisor.model.response.ShiftCreationResponse;
import com.scheduler.app.admin.service.EmployeeCreationService;
import com.scheduler.app.admin.service.HolidayCreationService;
import com.scheduler.app.utility.service.UtilityService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AdminControllerUnitTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AdminController adminController = new AdminController();

    private EmployeeCreationRequest request = new EmployeeCreationRequest();

    @Mock
    private EmployeeCreationService employeeCreationService;

    @Mock
    private UtilityService utilityService;

    private HolidayCreationResponse holidayReponse;

    private HolidayCreationRequest holidayCreationRequest = new HolidayCreationRequest();
    @Mock
    private HolidayCreationService holidayCreationService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Object[] controllers;
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    public void testCreateEmployee() throws Exception {
        EmployeeCreationResponse employeeCreationResponse = new EmployeeCreationResponse(REQUEST_STATUS.SUCCESS,true);
        request.setEmployeeNumber("123456");
        when(employeeCreationService.createNewEmployee(request)).thenReturn(employeeCreationResponse);
        mockMvc.perform(post("/admin/create/employee").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(request))).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testDeclareHoliday() throws Exception {
        holidayCreationRequest.setHolidayTitle("Holiday");
        holidayReponse = new HolidayCreationResponse(REQUEST_STATUS.SUCCESS, true);
        when(holidayCreationService.addNewHoliday(holidayCreationRequest)).thenReturn(holidayReponse);
        mockMvc.perform(post("/admin/create/holiday").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(holidayCreationRequest))).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetsStatistics() throws Exception {
        AdminInfoResponse adminInfoResponse = new AdminInfoResponse();
        when(utilityService.getStatistics(true)).thenReturn(adminInfoResponse);
        mockMvc.perform(get("/admin/fetch/info").param("onload", String.valueOf(true)))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void testLogShiftDuration() throws Exception {
        ShiftCreationRequest shiftCreationRequest = new ShiftCreationRequest();
        shiftCreationRequest.setShiftDuration(8);
        ShiftCreationResponse shiftCreationResponse = new ShiftCreationResponse(REQUEST_STATUS.SUCCESS,true);
        when(utilityService.logNewShiftDuration(shiftCreationRequest)).thenReturn(shiftCreationResponse);
        mockMvc.perform(post("/admin/shift").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(shiftCreationRequest))).andDo(print()).andExpect(status().isOk());
    }

}
