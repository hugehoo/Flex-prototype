package Flex.v2.controller;

import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import Flex.v2.service.MemberService;
import Flex.v2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    private final ScheduleService scheduleService;

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


}
