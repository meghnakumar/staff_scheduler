package com.scheduler.app.util;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;

@Component
public class DateUtil {

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return new Date(cal.getTimeInMillis());
    }
}
