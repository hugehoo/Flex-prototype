package Flex.v2.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Week {

    public static Map<String, LocalDate> weekFromTo(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        Map<String, LocalDate> fromToDate = new HashMap<>();

        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO : ExceptionHandler 추가 필요
        }

        calendar.setTime(date);
        calendar.add(Calendar.DATE, 2 - calendar.get(Calendar.DAY_OF_WEEK));

        LocalDate from = LocalDate.parse(dateFormat.format(calendar.getTime()));
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 8 - calendar.get(Calendar.DAY_OF_WEEK));
        LocalDate to = LocalDate.parse(dateFormat.format(calendar.getTime()));

        fromToDate.put("from", from);
        fromToDate.put("to", to);

        return fromToDate;
    }
}

