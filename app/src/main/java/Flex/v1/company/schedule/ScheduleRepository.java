package Flex.v1.company.schedule;

import Flex.v1.company.worktype.WorkType;
import Flex.v1.company.worktype.WorkTypeRepository;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 스케쥴의 저장소로 스케쥴을 일 단위로 저장한다.
 */
public class ScheduleRepository {


    /**
     * 스케쥴 저장소는 날짜별로 순서대로 뽑아내야 하기 때문에 LinkedHashMap 을 사용한다.
     */
    static Map<String, Schedule> weeklyScheduleMap = new LinkedHashMap<>();

    /**
     * 근무 유형 저장소에서 회사의 기본 근무 유형을 가져온다.
     */
    static WorkTypeRepository workTypeRepository;


    /**
     * 특정 날짜를 기준으로, 해당 주의 스케쥴을 가져온다.
     * @param YEAR 연도
     * @param MONTH 월
     * @param DATE 일
     * @return 입력한 날짜가 속한 주(week) 의 스케쥴을 리턴
     */
    public static Map<String, Schedule> getWeeklyScheduleByDate(int YEAR, int MONTH, int DATE) {

        WorkType basicWorkType = WorkTypeRepository.getBasicWorkType();

        Calendar cal = Calendar.getInstance();
        cal.set(YEAR, MONTH - 1, DATE);
        cal.getTime();

        setWeekdays(basicWorkType, cal);
        setWeekends(cal);

        return weeklyScheduleMap;
    }

    // 고민 1.
    // 주중 스케쥴은 회사의 고정 근무 유형을 기반으로 생성된다.
    // 그런데 미래의 어떤 날에 휴가를 계획하여, 특정일에 휴가를 저장해 둔다면?
    // 그럴 때는 스케쥴에 고정 근무 유형을 무작정 넣으면 안되고, 저장된 휴가를 가져와야한다. 어떻게 ?
    // for 문 돌리기 전에, 해당 날짜에 이미 데이터(연차 등)가 있다면, 고정 근무유형을 설정 하지 않는다.

    /**
     * 한 주의 평일 스케쥴을 세팅한다.
     * @param workType 근무 유형
     * @param cal Calendar 객체
     */
    public static void setWeekdays(WorkType workType, Calendar cal) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        for (int i = 1; i <= Calendar.DATE; ++i) {
            cal.add(Calendar.DATE, 1);
            String  date = formatter.format(cal.getTime());

            Schedule schedule = Schedule.builder()
                .date(date)
                .workType(workType.getName())
                .startTime(workType.getStartHour())
                .endTime(workType.getEndHour())
                .lunchBreak(1L)
                .build();

            weeklyScheduleMap.put(date, schedule);
//            weeklySchedule
        }
    }

    /**
     * 한 주의 주말 스케쥴을 세팅한다.
     * @param cal Calendar 객체
     */
    private static void setWeekends(Calendar cal) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        for (int i = 1; i <= 2; ++i) {
            cal.add(Calendar.DATE, 1);
            String  date = formatter.format(cal.getTime());

            Schedule schedule = Schedule.builder()
                .date(date)
                .workType("no work")
                .build();

            weeklyScheduleMap.put(date, schedule);
        }
    }

}
