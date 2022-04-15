package Flex.v2.service;


import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import Flex.v2.repository.MemberJpaRepository;
import Flex.v2.repository.ScheduleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleJpaRepository scheduleRepository;
    private final MemberJpaRepository memberRepository;

    public List<Schedule> getMemberSchedule(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(437L);
        Member member = memberOptional.orElse(new Member());
        return scheduleRepository.findScheduleByMember(member);
    }


}

// 전체 스케쥴을 가져온 다는 것은 schedule 테이블의 모든 스케쥴을 가져온다는 것인데 , 그런 기능은 필요하지 않다.