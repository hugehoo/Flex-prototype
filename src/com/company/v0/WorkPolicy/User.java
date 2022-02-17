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
}
