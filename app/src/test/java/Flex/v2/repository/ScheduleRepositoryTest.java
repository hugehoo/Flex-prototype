package Flex.v2.repository;

import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import Flex.v2.domain.ScheduleStatus;
import Flex.v2.exception.NotEnoughLeaveException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ScheduleRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public static int week = 6;

    @Test
    @Transactional
    @Rollback(value = false)
    void Test_7Days_Schedule_For_Each_Member() {

        // given
        Member member1 = saveNewMember("임성후");
        Member member2 = saveNewMember("김봉주");
        Member member3 = saveNewMember("전성환");

        // when
        List<Member> memberList = memberRepository.findAll();
        memberList.forEach(this::setWeekSchedule);

        // then
        assertEquals(7, scheduleRepository.findByMember(member1).size());
        assertEquals(LocalDate.of(2022, 4, 4), scheduleRepository.findByMember(member2).get(0).getDate());
        assertEquals(ScheduleStatus.WEEKEND, scheduleRepository.findByMember(member3).get(6).getScheduleStatus());
    }


    @Test
    @Transactional
    @Rollback(value = false)
    void When_Given_DayOff_Should_WorkHour_0F() {

        // given
        Member member1 = saveNewMember("임성후");
        Schedule schedule = createSchedule(member1, LocalDate.of(2022, 4, 4));


        // when
        Long scheduleId = scheduleRepository.save(schedule.setDayOff());

        // then
        schedule = scheduleRepository.findByMember(member1).get(0);

        assertEquals(ScheduleStatus.DAY_OFF, schedule.getScheduleStatus());
        assertEquals(0F, scheduleRepository.findById(scheduleId).getWorkHour());
    }


    @Test
    @Transactional
    @Rollback(value = false)
    void When_Given_Multiple_DayOff_() {

        // given
        Member member1 = saveNewMember("임성후");
        Schedule schedule = createSchedule(member1, LocalDate.of(2022, 4, 4));
        Schedule schedule2 = createSchedule(member1, LocalDate.of(2022, 4, 5));
        Schedule schedule3 = createSchedule(member1, LocalDate.of(2022, 4, 6));

        // when
        Long scheduleId = scheduleRepository.save(schedule.setDayOff());
        Long scheduleId2 = scheduleRepository.save(schedule2.setDayOff());
        Long scheduleId3 = scheduleRepository.save(schedule3.setHalfDayOff());

        // then
        assertEquals(0F, scheduleRepository.findById(scheduleId).getWorkHour());
        assertEquals(0F, scheduleRepository.findById(scheduleId2).getWorkHour());
        assertEquals(4F, scheduleRepository.findById(scheduleId3).getWorkHour());

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void When_LeaveCount_Under_Zero_Throws_NotEnoughLeaveException_For_DayOff() {

        // when
        Member member = saveNewMember("한승윤");
        for (int i = 0; i <= 9; i++) {
            Schedule schedule = createSchedule(member, LocalDate.now().plusDays(i));
            scheduleRepository.save(schedule.setDayOff());
        }

        // given
        Schedule schedule = createSchedule(member, LocalDate.now().plusDays(10));

        // then
        assertThrows(NotEnoughLeaveException.class, () -> schedule.setDayOff());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void Test_findByDate_Method() {

        // when
        Member member = saveNewMember("한승윤");
        for (int i = 0; i <= 9; i++) {
            Schedule schedule = createSchedule(member, LocalDate.now().plusDays(i));
            scheduleRepository.save(schedule.setHalfDayOff());
        }

        // given
        Schedule schedule = scheduleRepository.findByDate(member, LocalDate.now());
        Schedule nineDaysLaterSchedule = scheduleRepository.findByDate(member, LocalDate.now().plusDays(9));

        // then
        assertEquals(ScheduleStatus.HALF_DAY_OFF, schedule.getScheduleStatus());
        assertEquals(LocalDate.now(), schedule.getDate());

        assertEquals(ScheduleStatus.HALF_DAY_OFF, nineDaysLaterSchedule.getScheduleStatus());
        assertEquals(LocalDate.now().plusDays(9), nineDaysLaterSchedule.getDate());
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void When_LeaveCount_Under_Zero_Throws_NotEnoughLeaveException_For_HalfDayOff() {

        // when
        Member member = saveNewMember("한승윤");
        for (int i = 0; i <= 19; i++) {
            Schedule schedule = createSchedule(member, LocalDate.now().plusDays(i));
            scheduleRepository.save(schedule.setHalfDayOff());
        }

        // given
        Schedule schedule = createSchedule(member, LocalDate.now().plusDays(20));

        // then
        assertThrows(NotEnoughLeaveException.class, () -> schedule.setHalfDayOff());
    }

    private Member saveNewMember(String name) {
        Member member1 = Member.builder()
                .name(name)
                .build();

        memberRepository.save(member1);
        return member1;
    }

    private Schedule createSchedule(Member member, LocalDate date) {
        return Schedule.builder()
                .member(member)
                .date(date)
                .build();
    }


    public void setWeekSchedule(Member member) {

        for (int i = 0; i <= week; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            Schedule newSchedule;

            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                newSchedule = Schedule.setWeekendSchedule(member, date);
            } else {
                newSchedule = Schedule.setBasicSchedule(member, date);
            }
            scheduleRepository.save(newSchedule);
        }
    }
}
