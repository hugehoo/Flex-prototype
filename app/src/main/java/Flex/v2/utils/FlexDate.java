package Flex.v2.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class FlexDate {

    static Calendar calendar = Calendar.getInstance(Locale.KOREA);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Map<String, LocalDate> finalDateMap(String stringDate, boolean isFromTo) {
        Date date = parseDate(stringDate);
        calendar.setTime(date);
        return generateDateMap(isFromTo);
    }

    public static Map<String, LocalDate> generateDateMap(boolean isFromTo) {
        Map<String, LocalDate> dateMap = new HashMap<>();
        if (isFromTo) {
            calendar.add(Calendar.DATE, 2 - calendar.get(Calendar.DAY_OF_WEEK));
            LocalDate from = LocalDate.parse(dateFormat.format(calendar.getTime()));

            calendar.add(Calendar.DATE, 8 - calendar.get(Calendar.DAY_OF_WEEK));
            LocalDate to = LocalDate.parse(dateFormat.format(calendar.getTime()));

            dateMap.put("from", from);
            dateMap.put("to", to);
            return dateMap;
        }
        dateMap.put("specific", LocalDate.parse(dateFormat.format(calendar.getTime())));
        return dateMap;
    }

    public static Date parseDate(String stringDate) {
        Date date = new Date();
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            // TODO : ExceptionHandler 추가 필요
        }
        return date;
    }
}
