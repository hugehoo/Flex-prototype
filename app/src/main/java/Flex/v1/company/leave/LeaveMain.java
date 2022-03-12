package Flex.v1.company.leave;

public class LeaveMain {

    public void execute() {

        Leave refreshLeave = new Leave(
            "리프레시 휴가",
            "연속 사용",
            30,
            true,
            "paid",
            "all",
            true,
            false
        );

        // Builder 패턴 사용 -> 모든 인자가 null 로 반환,, why?
        //        Leave refreshLeave = new Leave.Builder("리프레시 휴가")
//            .leaveUnit("연속 사용")
//            .leaveDay(30)
//            .isIncludeHoliday(true)
//            .paidLeave("유급")
//            .sexualAdaption("all")
//            .approvalAdministrator(true)
//            .notifyAdministrator(false)
//            .build();

//        leaveRepository.saveLeave(refreshLeave);

        LeaveRepository leaveRepository = new LeaveRepository();
        leaveRepository.saveLeave(refreshLeave);
        leaveRepository.getLeave();

    }

}
