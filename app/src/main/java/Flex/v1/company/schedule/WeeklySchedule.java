package Flex.v1.company.schedule;

import Flex.v1.company.worktype.WorkType;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 주간 스케쥴 클래스로, 각각 월 ~ 일의 Schedule 클래스를 리스트로 갖는다.
 */
public class WeeklySchedule {

    /**
     * 개별 인원의 근무 유형을 나타낸다.
     */
    private WorkType workType;

    /**
     * 한 주의 시작일로, 월요일에 해당된다.
     */
    private Date startOfWeek;

    /**
     * 한 주의 마지막일로, 일요일에 해당된다.
     */
    private Date endOfWeek;

    private int weeklyTotalHour;

    /**
     * 주간 기본 근무 시간
     */
    private int basicWorkHour;

    /**
     * 주간 초과 근무 시간
     */
    private int overWorkHour;

    /**
     * 해당 주의 모든 스케쥴을 담은 리스트. 크기는 반드시 7이다. 어디서 담아주지 이걸. 이걸 기본 설정에 따라 담아줘야한다. 월에서 금까지는 고정 근무 설정에 따라
     * 담아주던가.
     */
    private List<Schedule> weeklySchedule;

    public void calculateWeeklyTotalHour() throws ParseException {

        for (Schedule schedule : this.weeklySchedule) {
            weeklyTotalHour += schedule.calculateWorkHours();
            overWorkHour += schedule.calculateOverWorkHours();
        }
        basicWorkHour = weeklyTotalHour - overWorkHour;
    }

    public Object showWeeklySummary() {
        Map<String, Integer> weeklySummary = new HashMap<>();
        weeklySummary.put("weeklyWorkHour", weeklyTotalHour);
        weeklySummary.put("weeklyBasicHour", weeklyTotalHour - overWorkHour);
        weeklySummary.put("weeklyOverHour", overWorkHour);

        return weeklySummary;
    }


}
