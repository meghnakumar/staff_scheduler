package admin.model;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.admin.model.entity.HolidayPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.sql.Date;

import static org.junit.Assert.assertEquals;


@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class HolidayPOJOTest {
    private HolidayPOJO holidayPOJO;
    String DATE = "2022-2-2";
    Integer ID = 1;
    String HOLIDAY_TITLE = "Holiday";
    @Test
    public void readHolidayPOJOTest() {

        holidayPOJO = new HolidayPOJO();
        holidayPOJO.setId(ID);
        holidayPOJO.setStartDate(Date.valueOf(DATE));
        holidayPOJO.setEndDate(Date.valueOf(DATE));
        holidayPOJO.setHolidayTitle(HOLIDAY_TITLE);

        assertEquals(ID, holidayPOJO.getId());
        assertEquals(Date.valueOf(DATE), holidayPOJO.getStartDate());
        assertEquals(Date.valueOf(DATE), holidayPOJO.getEndDate());
        assertEquals(HOLIDAY_TITLE,holidayPOJO.getHolidayTitle());


    }
}


