package Flex.v1.company.worktype;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkTypeTest {

    WorkType workType;
    List<String> workingDay;
    List<String> offDay;

    @BeforeEach
    void createWorkType() {
        workingDay = new ArrayList<>();
        workingDay.add("월");
        workingDay.add("화");
        workingDay.add("수");
        workingDay.add("목");
        workingDay.add("금");

        offDay = new ArrayList<>();
        offDay.add("일");
    }

    @Test
    @DisplayName("정규 근로시간이 8시간 보다 적을 때")
    void When_WorkingHourLessThan8() throws ParseException {
        workType = WorkType.builder()
                .startHour("09:10:00")
                .endHour("18:00:00")
                .lunchBreak(1L)
                .workingDay(workingDay)
                .offDay(offDay)
                .build();

        long expectedWorkingHour = 8;
        assertNotEquals(workType.countDailyWorkingHour(), expectedWorkingHour);
    }

    @Test
    @DisplayName("근로일과 휴일이 겹칠 때")
    void When_WorkingDay_OffDay_Duplicate(){
        offDay = new ArrayList<>();
        offDay.add("금");

        workType = WorkType.builder()
                .name("고정 출퇴근제")
                .workManagement("고정")
                .startHour("09:00:00")
                .endHour("18:00:00")
                .lunchBreak(1L)
                .workingDay(workingDay)
                .offDay(offDay)
                .build();

        long countOffDays = offDay.stream().filter(off -> workingDay.contains(off)).count();
        assertNotEquals(countOffDays, 0);
    }

    @Test
    @DisplayName("workingDay 가 null 일 때 Test")
    void When_WorkingDayNull() {

        workType = WorkType.builder()
                .name("고정 출퇴근제")
                .workManagement("고정")
                .startHour("06:00:00")
                .endHour("18:00:00")
                .lunchBreak(1L)
                .offDay(offDay)
                .build();

        assertThrows(NullPointerException.class, () ->
                workType.countWeeklyWorkingHour());
    }
}
