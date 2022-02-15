package com.company.v0.WorkPolicy;

public class User {

    private Long id;
    private String name;
    private boolean managerAuthority;

    public User authorizeManager(Long id) {
        if (authorize(id)) {
            return manager;
        } else {
            return null;
        }
    }

    public Work getSchedule() {
        return personalSchedule;
    }
}
