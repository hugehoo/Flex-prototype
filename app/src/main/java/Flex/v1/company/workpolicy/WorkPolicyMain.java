package Flex.v1.company.workpolicy;

public class WorkPolicyMain {

    public void execute() {

        WorkPolicy workPolicy1 = new WorkPolicy(
            "반차",
            "오전반차 혹은 오후반차",
            "팀내 공유",
            true,
            true,
            true,
            true,
            false
        );

        workPolicy1.notifyAdministrator();
    }
}
