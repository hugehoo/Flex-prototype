package com.company.v0.WorkPolicy;

public class User {

    private Long id;
    private String name;
    private boolean managerAuthority;

    public static Boolean isManagerAuthorized(Long id) {
        Boolean isManager = UserDb.findManagerById(id);
        return isManager;
    }

    public Work getSchedule(String date) {
        return personalSchedule;
    }

    public void inputBasicInfo() {
        System.out.println("기본정보 입력");
    }

    public void inputExtraInfo() {
        System.out.println("추가정보 입력");
    }
}
