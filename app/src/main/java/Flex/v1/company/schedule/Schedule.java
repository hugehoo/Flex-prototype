package Flex.v1.company.schedule;


import Flex.v1.company.workpolicy.WorkPolicy;
import Flex.v1.company.worktype.WorkType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 일간 스케쥴을 나타내는 속성과 기능을 나타내는 클래스 회사의 WorkType(근무 유형) 에 따라 기본 출퇴근 시간이 세팅된다.
 */
@Getter
@Setter
@Builder
public class Schedule {


    public Schedule(long userId, String date,
                    WorkPolicy workPolicy,
                    WorkType workType, String startTime,
                    String endTime, int totalWorkHour, int overWorkHour, long lunchBreak) {
        this.userId = userId;
        this.date = date;
        this.workPolicy = workPolicy;
        this.workType = workType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalWorkHour = totalWorkHour;
        this.overWorkHour = overWorkHour;
        this.lunchBreak = lunchBreak;
    }

    /**
     * Schedule 의 주체가 되는 User
     */
    private long userId;

    /**
     * 해당 스케쥴이 가지는 날짜
     */
    private String date;

    /**
     * 근무 정책 : 일반 근무, 연차, 외근, 원격근무 등 workPolicy 가 연차일 때는 휴가 제도가 무엇이냐에 따라 (종일/오전/오후) 나 (종일) 을 보여줘야한다.
     * 연차를 등록하는 순간, leave 클래스와 연결되어 연산해야한다. 복잡해지네.
     */
    private WorkPolicy workPolicy;

    /**
     * 근무 유형
     */
    private WorkType workType;

    /**
     * 출근 시간
     */
    private String startTime;

    /**
     * 퇴근 시간
     */
    private String endTime;

    /**
     * 전체 근무 시간
     */
    private int totalWorkHour;

    /**
     * 초과 근무 시간
     */
    private int overWorkHour;

    private final int minutesOfHour = 60;
    private final int secondsOfHour = 60000;
    private long lunchBreak;

    static Logger logger = LoggerFactory.getLogger(ScheduleMain.class);

    /**
     * 일(day) 초과 근무 시간을 계산한다.
     *
     * @return 초과 근무 시간
     * @throws ParseException
     */
    public long calculateOverWorkHours() throws ParseException {

        Long dailyWorkHour = calculateWorkHours();
        int basicWorkHour = 8;

        return (dailyWorkHour > basicWorkHour) ? dailyWorkHour - 8 : 0;

    }

    /**
     * 일 근무 시간을 계산합니다.
     *
     * @return 일 근무 시간
     * @throws ParseException
     */
    public Long calculateWorkHours() throws ParseException {
        SimpleDateFormat HHMMSS = new SimpleDateFormat("HH:mm:ss");
        try {
            Date end = HHMMSS.parse(this.endTime);
            Date start = HHMMSS.parse(this.startTime);
            long fromToHour = (end.getTime() - start.getTime());

            logger.info("근무 시간은 {} 시간 입니다.", (fromToHour / secondsOfHour / minutesOfHour) - lunchBreak);

            return (fromToHour / secondsOfHour / minutesOfHour) - lunchBreak;
        } catch (NullPointerException e) {
            // NPE 가 발생했다는 뜻은 휴일이라는 의미
            return 0L;
        }
    }

    public boolean changeWorkPolicy(WorkPolicy workPolicy) {
        this.workPolicy = workPolicy;
        return workPolicy.isNotifyAdministrator();
    }
}
