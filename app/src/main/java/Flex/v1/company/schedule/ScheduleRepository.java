package Flex.v1.company.schedule;

import Flex.v1.company.user.User;
import Flex.v1.company.worktype.WorkType;
import Flex.v1.company.worktype.WorkTypeRepository;
import java.text.SimpleDateFormat;
import java.util.*;

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
    static Map<Long, Map<String, Schedule>> personalScheduleMap = new HashMap<>();

    /**
     * 근무 유형 저장소에서 회사의 기본 근무 유형을 가져온다.
     */
    static WorkTypeRepository workTypeRepository;
    static WorkType personalWorkType;
    static WorkType dayOffWorkType;
    static User user;

    Logger logger = LoggerFactory.getLogger(ScheduleRepository.class);

    public Map<String, Schedule> getUserSchedule(long userId) {

        logger.info("{} 의 Schedule 을 리턴", user);
        return personalScheduleMap.get(userId);
    }


    /**
     * 특정 날짜를 기준으로, 해당 주의 스케쥴을 가져온다.
     *
     * @param year  연도
     * @param month 월
     * @param date  일
     * @return 입력한 날짜가 속한 주(week) 의 스케쥴을 리턴
     */
    public static Map<String, Schedule> getWeeklyScheduleByDate(int year, int month, int date) {

        WorkType basicWorkType = WorkTypeRepository.getBasicWorkType();

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date);
        cal.getTime();

        setWeekdays(basicWorkType, cal);
        setWeekends(cal);

        return weeklyScheduleMap;
    }

    public static void setScheduleAsLeave(String date) {

        // 날짜를 받았으니 해당 날짜를 repository 에서 가져온다.
        // repository 에 해당 날짜가 저장돼있지 않으면, 생성하여 저장한다.
        // 해당 날짜가 저장돼 있으면 그대로 리턴한다.
        // 리턴받은 Schedule 객체의 workType 을 leave 로 박으면 되나.

        dayOffWorkType = WorkType.builder()
                .name("반차")
                .build();

        Schedule schedule = ScheduleRepository.getScheduleByDate(date);
        schedule.setWorkType(dayOffWorkType);

    }

    // 특정 날짜의 근무유형을 변경하려면, 해당 날짜의 schedule 을 리턴받으면 된다. 굳이 맵 타입으로 받을 필요가 없다.
    public static Schedule getScheduleByDate(String date) {
        return weeklyScheduleMap.get(date);
    }

    /**
     * 한 주의 평일 스케쥴을 세팅한다.
     *
     * @param workType 근무 유형
     * @param cal      Calendar 객체
     */
    public static void setWeekdays(WorkType workType, Calendar cal) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        for (int i = 1; i <= Calendar.DATE; ++i) {
            cal.add(Calendar.DATE, 1);
            String date = formatter.format(cal.getTime());
            Schedule schedule = Schedule
                    .builder()
                    .date(date)
                    .workType(personalWorkType)
                    .startTime(workType.getStartHour())
                    .endTime(workType.getEndHour())
                    .lunchBreak(1L)
                    .build();

            weeklyScheduleMap.put(date, schedule);
        }
    }

    /**
     * 한 주의 주말 스케쥴을 세팅하기 위해 루프를 2회 돈다.
     *
     * @param cal Calendar 객체
     */
    private static void setWeekends(Calendar cal) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        for (int i = 1; i <= 2; ++i) {
            cal.add(Calendar.DATE, 1);
            String date = formatter.format(cal.getTime());

            Schedule schedule = Schedule.builder()
                    .date(date)
                    .workType(personalWorkType)
                    .build();

            weeklyScheduleMap.put(date, schedule);
        }
    }

}
