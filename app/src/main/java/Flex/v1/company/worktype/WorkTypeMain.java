package Flex.v1.company.worktype;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class WorkTypeMain {
    List<String> workingDay;
    List<String> offDay;

    /**
     * 일하는 날을 지정합니다.
     */
    public void setWorkingDay() {
        workingDay = new ArrayList<>();
        workingDay.add("월");
        workingDay.add("화");
        workingDay.add("수");
        workingDay.add("목");
        workingDay.add("금");
    }

    /**
     * 유급 휴일일을 지정합니다.
     */
    public void setOffDay() {
        offDay = new ArrayList<>();
        offDay.add("일");
    }

    public void execute() throws ParseException {
        setOffDay();
        setWorkingDay();
        WorkType workType = new WorkType(
            "고정 출퇴근제",
            "고정",
            "09:00:00",
            "18:00:00",
            1L,
            workingDay,
            offDay
        );

        int WeeklyHour = workType.countWeeklyWorkingHour();
        workType.checkStatutoryWorkingHour(WeeklyHour);
    }

}
