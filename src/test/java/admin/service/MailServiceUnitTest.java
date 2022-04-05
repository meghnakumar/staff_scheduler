package admin.service;

import com.scheduler.app.StaffSchedulerApplication;
import com.scheduler.app.admin.util.MailService;
import com.scheduler.app.staff.model.entity.EmpDetailPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = StaffSchedulerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceUnitTest {

    @Autowired
    MailService mailService;

    @Value("${mail-service.email}")
    private String STAFF_SCHEDULER_EMAIL_ID;

    @Value("${mail-service.firstname}")
    private String SENDER_FIRST_NAME;

    @Value("${mail-service.lastname}")
    private String SENDER_LAST_NAME;

    @Value("${mail-service.password}")
    private String STAFF_SCHEDULER_PASSWORD;

    @Test
    public void testSendMailToEmployee(){
        EmpDetailPOJO empDetailPOJO = new EmpDetailPOJO();
        empDetailPOJO.setLastName("last");
        empDetailPOJO.setFirstName("first");
        empDetailPOJO.setEmailId("imgandhirajan@gmail.com");
        boolean result = mailService.sendMailToEmployee(empDetailPOJO,"subject","message");
        assertTrue(result);
    }

    @Test
    public void testSendMailToEmployeeInvalidID(){
        EmpDetailPOJO empDetailPOJO = new EmpDetailPOJO();
        empDetailPOJO.setLastName("last");
        empDetailPOJO.setFirstName("first");
        empDetailPOJO.setEmailId("invalid");
        boolean result = mailService.sendMailToEmployee(empDetailPOJO,"subject","message");
        assertFalse(result);
    }

    @Test
    public void testGetterSetter(){
        assertEquals(SENDER_FIRST_NAME,mailService.getSENDER_FIRST_NAME());
        assertEquals(STAFF_SCHEDULER_EMAIL_ID,mailService.getSTAFF_SCHEDULER_EMAIL_ID());
        assertEquals(STAFF_SCHEDULER_PASSWORD,mailService.getSTAFF_SCHEDULER_PASSWORD());
    }
}
