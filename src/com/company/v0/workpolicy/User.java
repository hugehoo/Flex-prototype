package com.company.v0.workpolicy;

public class User {

    private Long id;
    private String name;
    private boolean managerAuthority;
    UserDb db = new UserDb();

    public static User findManager(Long id) {
        User isManager = UserDb.findManagerById(id);
        return isManager;
    }

    public Work getSchedule(String date) {
        return personalSchedule;
    }

    public void inputBasicAndExtraInfo() {
        System.out.println("기본정보 입력");
        System.out.println("부가정보 입력");
    }

    public void saveAsWaitingUser() {
        db.saveWaitingUser();
        System.out.println("초대 승낙 대기중인 멤버를 임시 저장");
    }

    public void inputPersonalInfo(Object personalInfo) {
        db.savePersonalInfo(personalInfo);
    }
}
