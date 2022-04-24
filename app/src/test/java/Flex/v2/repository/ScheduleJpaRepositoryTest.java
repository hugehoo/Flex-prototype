package Flex.v2.repository;

import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ScheduleJpaRepositoryTest {

    @Autowired
    ScheduleJpaRepository scheduleRepository;

    @Autowired
    MemberJpaRepository memberRepository;

    LocalDate yetCreatedDate = LocalDate.of(2022, 5, 1);
    LocalDate alreadyCreatedDate = LocalDate.of(2022, 4, 19);

    @Test
    @DisplayName("특정 멤버의 특정 일자별 스케쥴을 검증한다.")
    public void getMemberNowSchedule() {

        Member member = getMember(437L);
        List<Schedule> scheduleList = generateScheduleList(member, alreadyCreatedDate);

        for (Schedule schedule : scheduleList) {
            assertEquals(schedule.getDate(), LocalDate.of(2022, 4, 19));
        }
    }

    @Test
    @DisplayName("특정 날짜의 스케줄 조회 시, 스케줄이 존재하지 않으면 기본 스케줄을 생성한다.")
    void When_Schedule_Not_Exist_Create_Basic_Schedule() {

        Member member = getMember(437L);
        List<Schedule> scheduleList = generateScheduleList(member, yetCreatedDate);

        for (Schedule schedule : scheduleList) {
            assertEquals(yetCreatedDate, schedule.getDate());
        }
    }

    @Test
    @DisplayName("특정 날짜의 스케줄 조회 시, 스케줄이 이미 존재하면 해당 스케쥴을 반환한다.")
    void When_Schedule_Already_Exist_Return_the_Schedule() {

        Member member = getMember(437L);
        List<Schedule> scheduleList = generateScheduleList(member, alreadyCreatedDate);

        for (Schedule schedule : scheduleList) {
            assertEquals(alreadyCreatedDate, schedule.getDate());
            assertNotEquals(yetCreatedDate, schedule.getDate());
        }
    }

    private Member getMember(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        return memberOptional.orElse(new Member());
    }

    public List<Schedule> generateScheduleList(Member member, LocalDate localDate) {
        Schedule newSchedule = Schedule.setBasicSchedule(member, localDate);
        List<Schedule> scheduleList = scheduleRepository.findScheduleByMemberAndDate(member, localDate)
                .orElse(new ArrayList<>());
        scheduleList.add(newSchedule);
        return scheduleList;
    }
}