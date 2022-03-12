package Flex.v1.company.dayoff;

import java.text.SimpleDateFormat;
import lombok.Getter;

/**
 * 회사의 휴일을 관리하는 클래스
 */
@Getter
public class CompanyDayOff {

    private DayOffRepository offDaysRepository = new DayOffRepository();

    /**
     * 새로 생성하는 휴일 명칭
     */
    private String name;

    /**
     * 매해 반복 여부
     */
    private boolean annualOffDay;

    /**
     * 휴일 날짜
     */
    private SimpleDateFormat offDate;

    public CompanyDayOff(String name, boolean annualOffDay, SimpleDateFormat offDate) {
        this.name = name;
        this.annualOffDay = annualOffDay;
        this.offDate = offDate;
    }

    @Override
    public String toString() {
        return "CompanyOffDays{" +
            " name='" + name + '\'' +
            ", annualOffDay=" + annualOffDay +
            ", offDate=" + offDate +
            '}';
    }
}
