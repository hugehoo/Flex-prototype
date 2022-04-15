package Flex.v2.service;


import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import Flex.v2.domain.Week;
import Flex.v2.repository.MemberJpaRepository;
import Flex.v2.repository.ScheduleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleJpaRepository scheduleRepository;
    private final MemberJpaRepository memberRepository;
    private final MemberService memberService;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

    LocalDate from;
    LocalDate to;

    public List<Schedule> getMemberSchedule(Long id) {

        Member member = memberService.getMemberById(id);

        return scheduleRepository.findScheduleByMember(member);
    }

    public Schedule getMemberNowSchedule(Long id) {

        Member member = memberService.getMemberById(id);

        return scheduleRepository.findScheduleByMemberAndDate(member, LocalDate.now());
    }

    public List<Schedule> getMemberWeeklySchedule(Long id, String date) {

        Member member = memberService.getMemberById(id);

        Map<String, LocalDate> weekMap = Week.weekFromTo(date);
        from = weekMap.get("from");
        to = weekMap.get("to");

        return scheduleRepository.findScheduleByMemberAndDateBetween(member, from, to);
    }
}

// 전체 스케쥴을 가져온 다는 것은 schedule 테이블의 모든 스케쥴을 가져온다는 것인데 , 그런 기능은 필요하지 않다.