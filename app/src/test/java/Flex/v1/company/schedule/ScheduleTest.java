package Flex.v1.company.schedule;

import Flex.v1.company.worktype.WorkType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ScheduleTest {

    static WorkType personalWorkType;
    Schedule schedule;

    @Test
    void Calculate_Overwork_Time() throws ParseException {
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

    @Test
    @DisplayName("출퇴근 시간이 없으면, NullPointerException 을 던진다") // 휴일로 간주하는게 맞을거 같은데
    void When_No_Start_End_Time_Throws_NPException() throws ParseException {

        Schedule schedule = Schedule
                .builder()
                .userId(123L)
                .date("2022-03-28")
                .workType(personalWorkType)
                .build();

        assertEquals(-1L, schedule.calculateWorkHours());
    }

    @Test
    @DisplayName("특정 날짜를 휴가로 지정하면, 해당 날짜의 스케쥴은 휴가가 된다.")
    void When_Set_Leave_Schedule_Changed() {
        // 이 구문은 어떻게 쓰는거지?
//        when(scheduleRepository.getUserSchedule(anyInt())).thenReturn(null);
//        assertNull(scheduleRepository.getUserSchedule(anyInt()));

        Schedule.setLeave("2022-05-19");
        Schedule schedule = ScheduleRepository.personalScheduleMap.get("2022-05-19");
        assertEquals("반차", schedule.getWorkType().getName());

    }

    @Test
    @DisplayName("휴가를 지정할 때 날짜가 없다면, NullPointerException 을 던진다.")
    void When_Leave_Date_IS_Null_Throws_NPE() {
        assertThrows(NullPointerException.class, () -> Schedule.setLeave(null));
    }

}