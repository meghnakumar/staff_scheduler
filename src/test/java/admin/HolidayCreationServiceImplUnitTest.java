package admin;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.constants.REQUEST_STATUS;
import com.scheduler.app.admin.model.entity.HolidayPOJO;
import com.scheduler.app.admin.model.repo.HolidayRepository;
import com.scheduler.app.admin.model.request.HolidayCreationRequest;
import com.scheduler.app.admin.model.response.HolidayCreationResponse;
import com.scheduler.app.admin.service.HolidayCreationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class HolidayCreationServiceImplUnitTest {

    @InjectMocks
    private HolidayCreationServiceImpl holidayCreationService = new HolidayCreationServiceImpl();

    @Mock
    private HolidayRepository holidayRepo;

    @Mock
    private HolidayPOJO holidayPOJO;

    private HolidayCreationRequest holidayCreationRequest;
    private HolidayCreationResponse holidayCreationResponse;

    @Test
    public void addNewHolidayTest(){
        String start_date = "2022-05-01";
        String end_date = "2022-05-05";
        Date startdate = Date.valueOf(start_date);
        Date enddate = Date.valueOf(end_date);
        holidayCreationRequest = new HolidayCreationRequest();
        holidayCreationRequest.setHolidayTitle("Christmas");
        holidayCreationRequest.setHolidayId(12);
        holidayCreationRequest.setStartDate(startdate);
        holidayCreationRequest.setEndDate(enddate);
        when(holidayRepo.saveAndFlush(any())).thenReturn(holidayPOJO);
        holidayCreationResponse = holidayCreationService.addNewHoliday(holidayCreationRequest);
        assertEquals(holidayCreationResponse.getStatus(),(REQUEST_STATUS.SUCCESS));


    }


}
