package Flex.v2.repository;

import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;



@SpringBootTest
class ScheduleJpaRepositoryTest {

    @Autowired
    ScheduleJpaRepository scheduleRepository;

    @Autowired
    MemberJpaRepository memberRepository;

    @Test
    public void testRepository() {

        Optional<Member> memberOptional = memberRepository.findById(437L);
        Member member = memberOptional.orElse(new Member());

        List<Schedule> scheduleList = scheduleRepository.findScheduleByMember(member);
        for (Schedule schedule : scheduleList) {
            System.out.println(schedule.getId());
            System.out.println(schedule.getScheduleStatus());
            System.out.println(schedule.getDate());
            System.out.println(schedule.getWorkHour());

            System.out.println(" ");
        }

    }

}