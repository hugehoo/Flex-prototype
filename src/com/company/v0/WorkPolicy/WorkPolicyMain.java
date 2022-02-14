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
}
