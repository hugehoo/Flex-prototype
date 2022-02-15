package com.company.v0.WorkPolicy;

/**
 * 2022-02-15
 */
public class WorkPolicyMain {

    public void processForCreateWorkPolicy() {
        // 매니저인지 검증 후
        User manager = user.authorizeManager(id);

        // 매니저는 근무정책을 생성할 수 있다.
        WorkPolicy policy = manager.createWorkPolicy();

        policy.setName();
        policy.setExplain();
        policy.setOptions();
        policy.save();
    }

    public void updateWorkPolicy() {
        User user = new User();
        User manager = user.authorizeManager(id);

        WorkPolicy policy = manager.updateWorkPolicy(policyId);

        policy.builder()
            .name("외근")
            .explain("외부에서 근무")
            .build();
        policy.save();
    }

    public void chooseWorkPolicy() {
        User user = new User();
        Work myWorkSchedule = user.getSchedule();

        myWorkSchedule.builder()
            .date(today)
            .policy("외근")
            .time("11:00 - 17:00")
            .build();

        myWorkSchedule.save();
    }
}
