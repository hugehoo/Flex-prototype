package com.company.v0.WorkPolicy;

/**
 * 2022-02-15
 */
public class WorkPolicyMain {

    public void processForCreateWorkPolicy() {
        // 매니저인지 검증 후
        User manager = User.isManagerAuthorized(id);

        // 매니저는 근무정책을 생성할 수 있다.
        WorkPolicy policy = manager.createWorkPolicy();

        policy.setName();
        policy.setExplain();
        policy.setOptions();
        policy.save();
    }

    public void updateWorkPolicy() {
        User manager = User.isManagerAuthorized(id);

        WorkPolicy policy = manager.updateWorkPolicy(policyId);

        policy.builder()
            .name("외근")
            .explain("외부에서 근무")
            .build();
        policy.save();
    }

    public void setDailyWorkPolicy() {
        User user = new User();
        Work dailyWorkSchedule = user.getSchedule(date); // date : 근무정책을 적용할 날짜

        dailyWorkSchedule.builder()
            .policy("외근")
            .time("11:00 - 17:00")
            .build();

        dailyWorkSchedule.save();
    }

    public void setCompanyInformation(User id, String infos) {
        User.isManagerAuthorized(id) ? Company.setCompanyInfo(infos) : null;
    }

    public void inviteNewUserByEmail() {
        User manager = User.isManagerAuthorized(id);
        User notInvitedYetUser = new User();

        notInvitedYetUser.inputBasicAndExtraInfo();
        notInvitedYetUser.saveAsWaitingUser();

        sendEmail();
    }

    private void sendEmail() {
        System.out.println("send Email to New User");
    }
}
