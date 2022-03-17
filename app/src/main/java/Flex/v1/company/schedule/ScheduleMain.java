package Flex.v1.company.schedule;

import java.text.ParseException;
import java.util.Map;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ScheduleMain {

    static Logger logger = LoggerFactory.getLogger(ScheduleMain.class);

    public static void main(String[] args) throws ParseException {

        long overWorkHours = 0L;
        Map<String, Schedule> weeklySchedule =
            ScheduleRepository.getWeeklyScheduleByDate(2022, 5, 18);


        for (String s : weeklySchedule.keySet()) {

            logger.info("{} : {}", weeklySchedule.get(s).getDate(),  weeklySchedule.get(s).getWorkType());

            overWorkHours += weeklySchedule.get(s).calculateOverWorkHours();
        }
        logger.info("이번주의 초과근무는 {} 시간 입니다.", overWorkHours);
        // 보여준 다음엔, 개별 스케쥴을 수정할 수 있어야겠지.
    }

}
