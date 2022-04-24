package Flex.v2.service;


import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import Flex.v2.repository.MemberJpaRepository;
import Flex.v2.repository.ScheduleJpaRepository;
import Flex.v2.utils.FlexDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

// TODO : 다른 도메인의 Service Layer 에 의존하는 것이 맞는지, Repo Layer 에 의존하는 것이 맞는지.
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleJpaRepository scheduleRepository;
    private final MemberJpaRepository memberRepository;
    private final MemberService memberService;

    LocalDate from;
    LocalDate to;

    public List<Schedule> getSchedule(Long id) {
        Member member = memberService.getMemberById(id);
        return scheduleRepository.findScheduleByMember(member);
    }

    public List<Schedule> getTodaySchedule(Long id) {
        Member member = memberService.getMemberById(id);
        return scheduleRepository
                .findScheduleByMemberAndDate(member, LocalDate.now())
                .orElse(new ArrayList<>());
    }

    public List<Schedule> getSpecificDateSchedule(Long id, String date) {
        Member member = memberService.getMemberById(id);
        LocalDate specificDate = FlexDate.finalDateMap(date, false).get("specific");
        return scheduleRepository
                .findScheduleByMemberAndDate(member, specificDate)
                .orElse(new ArrayList<>());
    }

    public List<Schedule> getWeeklySchedule(Long id, String date) {
        Member member = memberService.getMemberById(id);
        Map<String, LocalDate> weekMap = FlexDate.finalDateMap(date, true);
        from = weekMap.get("from");
        to = weekMap.get("to");

        return scheduleRepository.findScheduleByMemberAndDateBetween(member, from, to);
    }
}

// 전체 스케쥴을 가져온 다는 것은 schedule 테이블의 모든 스케쥴을 가져온다는 것인데 , 그런 기능은 필요하지 않다.