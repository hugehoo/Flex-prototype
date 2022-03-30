package Flex.v1.company.schedule;

import java.text.ParseException;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ScheduleMain {

    static Logger logger = LoggerFactory.getLogger(ScheduleMain.class);

    public void checkWeeklySchedule(int year, int month, int date) throws ParseException {

        long overWorkHours = 0L;
        Map<String, Schedule> weeklySchedule =
            ScheduleRepository.getWeeklyScheduleByDate(year, month, date);

        for (String s : weeklySchedule.keySet()) {
            overWorkHours += weeklySchedule.get(s).calculateOverWorkHours();
        }
        logger.info("이번주의 초과근무는 {} 시간 입니다.", overWorkHours);
    }


    public static void main(String[] args) throws ParseException {

        ScheduleMain scheduleMain = new ScheduleMain();

        /**
         * 주간 스케쥴을 확인합니다.
         */
        scheduleMain.checkWeeklySchedule(2022, 5, 18);

    }

}
