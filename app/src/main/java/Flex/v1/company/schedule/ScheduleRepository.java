package Flex.v1.company.schedule;

import Flex.v1.company.user.User;
import Flex.v1.company.worktype.WorkType;
import Flex.v1.company.worktype.WorkTypeRepository;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 스케쥴의 저장소로 스케쥴을 일 단위로 저장한다.
 */
public class ScheduleRepository {

    /**
     * 스케쥴 저장소는 날짜별로 순서대로 뽑아내야 하기 때문에 LinkedHashMap 을 사용한다.
     */
    static Map<String, Schedule> weeklyScheduleMap = new LinkedHashMap<>();
    static Map<String, Schedule> personalScheduleMap = new LinkedHashMap<>();

    /**
     * 근무 유형 저장소에서 회사의 기본 근무 유형을 가져온다.
     */
    static WorkTypeRepository workTypeRepository;
    static WorkType personalWorkType;
    static User user;

    Logger logger = LoggerFactory.getLogger(ScheduleRepository.class);

    public Schedule getUserSchedule(long userId) {
        logger.info("{} 의 Schedule 을 리턴", user);
        return personalScheduleMap.get(userId);
    }


    /**
     * 특정 날짜를 기준으로, 해당 주의 스케쥴을 가져온다.
     * @param year 연도
     * @param month 월
     * @param date 일
     * @return 입력한 날짜가 속한 주(week) 의 스케쥴을 리턴
     */
    public static Map<String, Schedule> getWeeklyScheduleByDate(int year, int month, int date) {

        WorkType basicWorkType = WorkTypeRepository.getBasicWorkType();

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date);
        cal.getTime();

        // 하나의 세트로 동작해야하지 않나. ? 원자적으로 쪼개는 것도 의미는 알겠는데...
        setWeeklySchedule(basicWorkType, cal);

        return weeklyScheduleMap;
    }

    // 특정 날짜의 근무유형을 변경하려면, 해당 날짜의 schedule 을 리턴받으면 된다.
    // 굳이 맵 타입으로 받을 필요가 없다.
    public static Schedule getScheduleByDate(String date) {
        return weeklyScheduleMap.get(date);
    }

    /**
     * 한 주의 평일 스케쥴을 세팅한다.
     * @param workType 근무 유형
     * @param cal Calendar 객체
     */
    public static void setWeeklySchedule(WorkType workType, Calendar cal) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        for (int i = 1; i <= Calendar.DATE; ++i) {
            cal.add(Calendar.DATE, 1);
            String date = formatter.format(cal.getTime());
            Schedule schedule = Schedule
                .builder()
                .date(date)
                .workType(workType)
                .startTime(workType.getStartHour())
                .endTime(workType.getEndHour())
                .lunchBreak(1L)
                .build();

            weeklyScheduleMap.put(date, schedule);
        }

        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        for (int i = 1; i <= 2; ++i) {
            cal.add(Calendar.DATE, 1);
            String date = formatter.format(cal.getTime());
            Schedule schedule = Schedule.builder()
                    .date(date)
                    .build();

            weeklyScheduleMap.put(date, schedule);
        }
    }

}
