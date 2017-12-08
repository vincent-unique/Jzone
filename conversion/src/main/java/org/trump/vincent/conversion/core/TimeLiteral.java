package org.trump.vincent.conversion.core;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent on 2017/9/28 0028.
 */
public class TimeLiteral {
    /**
     * Enumeration for Date format
     */
    public static enum DATE_FORMAT{
        DEFAULT("yyyy-MM-dd"),
        YYYYMMDD("yyyyMMdd"),
        YYYYMMDDHHMMSS("YYYYMMDDHHMMSS"),
        SLASH("yyyy/MM/dd"),
        YYYYMMDDHHMMSS_SLASH("yyyy/MM/dd HH:mm:ss"),
        YYYYMMDDHHMMSS_ALLSLASH("yyyy/MM/dd/HHmmss")
        ;

        public String getPattern() {
            return pattern;
        }

        private String pattern;
        DATE_FORMAT(String pattern){
            this.pattern = pattern;
        }
    }

    /**
     * parse java.util.Date from time string sequences with certain date format pattern
     * @param origin
     * @param pattern
     * @return
     */
    public static java.util.Date parseDate(String origin ,String pattern){
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(origin);
        }catch (ParseException pe){
            pe.printStackTrace();
        }
        return null;
    }

    public static String fromDate(java.util.Date date,String pattern){
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String fromSqlDate(java.sql.Date date,String pattern){
        java.util.Date _date = new Date(date.getTime());
        return fromDate(_date,pattern);
     }

     public static String fromTimestamp(java.sql.Timestamp date,String pattern){
        java.util.Date _date = new Date(date.getTime());
        return fromDate(_date,pattern);
     }

     public static String fromSqlTime(java.sql.Time time,String pattern){
         java.util.Date date = new Date(time.getTime());
         return fromDate(date,pattern);
     }

    public static void main(String[] args) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String date = fromDate(time, DATE_FORMAT.YYYYMMDDHHMMSS_ALLSLASH.getPattern());
        System.out.print(date);
    }
}
