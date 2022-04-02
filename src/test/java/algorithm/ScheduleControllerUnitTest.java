package algorithm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.algorithm.controller.ScheduleController;
import com.scheduler.app.algorithm.model.request.ScheduleOutputRequest;
import com.scheduler.app.algorithm.model.response.ScheduleOutputResponse;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ScheduleControllerUnitTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ScheduleController scheduleController;

    @Mock
    private SchedulerService schedulerService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Object[] controllers;
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Test
    public void testGetScheduleByShift() throws Exception {
        ScheduleOutputResponse scheduleOutputResponse = new ScheduleOutputResponse(REQUEST_STATUS.SUCCESS,true,null);
        ScheduleOutputRequest scheduleOutputRequest = new ScheduleOutputRequest();
        when(schedulerService.getScheduleByDateTimeDepartment(scheduleOutputRequest)).thenReturn(scheduleOutputResponse);
        mockMvc.perform(post("/schedule/fetch").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(scheduleOutputRequest))).andDo(print()).andExpect(status().isOk());
    }
}
