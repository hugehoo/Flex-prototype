package Flex.v2.repository;

import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import org.junit.jupiter.api.DisplayName;
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

        Member member1 = Member.builder()
                .name("임성후")
                .build();
        Long saveId = memberRepository.save(member1);

        Member member2 = Member.builder()
                .name("김봉주")
                .build();
        Long saveId2 = memberRepository.save(member2);

        Member member3 = Member.builder()
                .name("전성환")
                .build();
        Long saveId3 = memberRepository.save(member3);


        List<Member> memberList = memberRepository.findAll();
        memberList.forEach(member -> {
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
        });
        assertEquals(7, scheduleRepository.findByMember(member1).size());
        assertEquals(member1, scheduleRepository.findByMember(member1).get(0).getMember());
        assertEquals(LocalDate.now(), scheduleRepository.findByMember(member1).get(0).getDate());
    }

}
