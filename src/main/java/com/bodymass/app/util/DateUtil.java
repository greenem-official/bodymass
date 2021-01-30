package com.bodymass.app.util;

import java.sql.Date;
import java.util.Calendar;

public class DateUtil {
    public static Date today() {
        return new java.sql.Date(new java.util.Date().getTime());
    }

    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new Date(c.getTimeInMillis());
    }

    public static Date subtractDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -days);
        return new Date(c.getTimeInMillis());
    }

    public static void main(String[] args) {
        Date today = today();
        Date sevenDaysAgo = subtractDays(today, 7);
        System.out.println(sevenDaysAgo);
    }
}
