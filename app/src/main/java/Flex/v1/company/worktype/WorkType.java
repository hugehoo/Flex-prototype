package Flex.v1.company.worktype;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 회사의 근무유형을 생성, 관리하는 클래스
 */
@Getter
@Builder
public class WorkType {

    /*
     근무 유형 이름
     */
    private String name;

    /*
     근무 유형 종류 (고정/시차/선택적/스케쥴)
     */
    private String workManagement;

    /*
     출근 시간
     */
    private String startHour;

    /*
     퇴근 시간
     */
    private String endHour;

    /*
     일 휴게 시간
     */
    private long lunchBreak;

    /*
     일하는 날
     */
    private List<String> workingDay;

    /*
     유급 휴일
     */
    private List<String> offDay;

    /*
     법정 근로시간 (주간)
     */
    private final int StatutoryWorkingHour = 40;

    public WorkType(String name, String workManagement, String startHour, String endHour,
        long lunchBreak, List<String> workingDay, List<String> offDay) {
        this.name = name;
        this.workManagement = workManagement;
        this.startHour = startHour;
        this.endHour = endHour;
        this.lunchBreak = lunchBreak;
        this.workingDay = workingDay;
        this.offDay = offDay;
    }

    private static final Logger logger = LoggerFactory.getLogger(WorkType.class);

    /**
     * 출퇴근 시간을 계산하여 하루 근무시간을 계산합니다.
     *
     * @return 일 근무시간
     * @throws ParseException
     */
    public long countDailyWorkingHour() throws ParseException {

        SimpleDateFormat HHMMSS = new SimpleDateFormat("HH:mm:ss");
        Date end = HHMMSS.parse(this.endHour);
        Date start = HHMMSS.parse(this.startHour);
        long dailyHour = ((end.getTime() - start.getTime()) / 60000 / 60) - this.lunchBreak;

        logger.info("하루 {} 시간 근무 ", dailyHour);

        return dailyHour;
    }

    /**
     * 주간 근무시간을 계산합니다.
     * @return 주 근무시간
     * @throws ParseException
     */
    public int countWeeklyWorkingHour() throws ParseException {
        int Size = this.workingDay.size();
        int dailyHour = (int) countDailyWorkingHour();

        logger.info("주 {} 시간 근무 ", Size * dailyHour);
        return Size * dailyHour;
    }

    /**
     * 주간 법정 근로시간을 준수하는지 확인합니다.
     * @param WeeklyWorkingHour
     */
    public boolean checkStatutoryWorkingHour(int WeeklyWorkingHour){
        if (StatutoryWorkingHour >= WeeklyWorkingHour) {
            logger.info("적법한 설정입니다");
            return true;
        } else {
            logger.info("법정 근로시간을 준수해주세요");
            return false;
        }
    }
}
