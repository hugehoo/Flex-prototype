package com.company.v0.dayoff;

import java.util.HashMap;

public class DayOff {

    private final String startDay;
    private final String endDay;
    private final String currentDayOffPolicy = Company.currentDayOffPolicy;

    public DayOff(String startDay, String endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int calculateDayOffRange(String[] dayOffArray) {
        return dayOffArray.length;
    }

    public void setEachDayDayOff(HashMap<String, String> dayOffObject) {
        // db에 저장
        db.save(dayOffObject);
    }

    public static void showCurrentDayOffPolicy() {
        String currentPolicy = Company.currentDayOffPolicy;
        System.out.println("current Policy is " + currentPolicy);
    }

    public void save() {
        System.out.println("save the DayOff(s)");
    }
}
