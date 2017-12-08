package org.trump.vincent.conversion.core;

import java.util.Calendar;

/**
 * Created by Vincent on 2017/10/9 0009.
 */
public class CalendarLiteral {

    /**
     * convert of java.util.Date type to java.util.Calendar
     * @param date
     * @return
     */
    public static Calendar date2Calendar(java.util.Date date){
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        return calendar;
    }

    /**
     * convert of java.sql.Date type to java.util.Calendar
     * @param date
     * @return
     */
    public static Calendar sqlDate2Calendar(java.sql.Date date){
        Calendar calendar = Calendar.getInstance();
        if (date!=null) {
//            java.util.Date _date = date;
            calendar.setTime(date);
        }
        return calendar;
    }


    /**
     * convert of java.sql.Timestamp type to java.util.Calendar
     * @param timestamp
     * @return
     */
    public static Calendar timestamp2Calendar(java.sql.Timestamp timestamp){
        Calendar calendar = Calendar.getInstance();
        if(timestamp != null){
            calendar.setTime(timestamp);
        }
        return calendar;
    }

    /**
     * convert of java.util.Calendar type to java.util.Date
     * @param calendar
     * @return
     */
    public static java.util.Date calendar2Date(java.util.Calendar calendar){
        if(calendar != null){
            return calendar.getTime();
        }
        return null;
    }
}
