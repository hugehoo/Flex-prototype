package Flex.v1.company.workpolicy;

public class WorkPolicyMain {

    public void execute() {

        WorkPolicy workPolicy1 = WorkPolicy
                .builder()
                .name("재택 근무")
                .workPolicyInfo("자택에서 근무합니다.")
                .notification("팀")
                .paid(true)
                .isNightWork(false).isWeekendWork(false)
                .approvalAdministrator(true)
                .notifyAdministrator(false)
                .build();

        workPolicy1.notifyAdministrator();
    }
}
