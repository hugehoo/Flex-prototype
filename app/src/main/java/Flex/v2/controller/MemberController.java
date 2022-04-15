package Flex.v2.controller;

import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import Flex.v2.service.MemberService;
import Flex.v2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    private final ScheduleService scheduleService;

//    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

    @GetMapping("/")
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    @GetMapping("/{id}")
    public Member getOneMember(@PathVariable("id") Long id) {
        return memberService.findOne(id);
    }


    @GetMapping("schedule/{memberId}")
    public List<Schedule> getMemberSchedule(@PathVariable("memberId") Long id) {
        return scheduleService.getMemberSchedule(id);
    }

    @GetMapping("schedule/{memberId}/now")
    public Schedule getMemberNowSchedule(@PathVariable("memberId") Long id) {
        return scheduleService.getMemberNowSchedule(id);
    }

    @GetMapping("schedule/{memberId}/{date}")
    public List<Schedule> getMemberWeeklySchedule(
            @PathVariable("memberId") Long id,
            @PathVariable("date") String date
    ) {
        return scheduleService.getMemberWeeklySchedule(id, date);
    }

}
