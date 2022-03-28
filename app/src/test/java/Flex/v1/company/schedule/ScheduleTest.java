package Flex.v1.company.schedule;

import Flex.v1.company.worktype.WorkType;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {


    static WorkType personalWorkType;
    Schedule schedule;

    @Test
    void 근무시간중_초과된_근무시간_계산() throws ParseException {

        schedule = Schedule
                .builder()
                .userId(123L)
                .date("2022-03-28")
                .workType(personalWorkType)
                .startTime("09:00:00")
                .endTime("21:10:00")
                .lunchBreak(1L)
                .build();

        assertEquals(190,  schedule.calculateOverWorkHours());

    }

    @Test
    void 출퇴근시간에따라_하루근무시간_계산() throws ParseException {
        schedule = Schedule
                .builder()
                .userId(123L)
                .date("2022-03-28")
                .workType(personalWorkType)
                .startTime("08:00:00")
                .endTime("17:10:00")
                .lunchBreak(2L)
                .build();
        assertEquals(430, schedule.calculateWorkHours());
    }
}