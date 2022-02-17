package com.company.v0.WorkPolicy;

// Repository
public class UserDb {

    public static Boolean findManagerById(Long id) {
        System.out.println("find Manager");
        return true;
    }

    // 승낙 대기중인 유저를 저장한다.
    public void saveWaitingUser() {
        System.out.println("이메일 승낙 전인 유저를 저장한다.");
    }

    // 승낙 대기중인 유저를 반환한다.
    public static User findWaitingUserById(Long id) {
        System.out.println("id를 조회하여 승낙 대기중인 유저를 반환한다.");
    }

    public void savePersonalInfo(Object personalInfo) {
        System.out.println("이메일 승낙 후 사용자가 직접 입력한 개인 정보를 저장한다.");
    }
}
