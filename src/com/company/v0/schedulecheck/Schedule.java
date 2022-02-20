package com.company.v0.schedulecheck;

import java.util.List;

public class Schedule {

    private String workPolicy;

    public String getWorkPolicy() {
        return workPolicy;
    }

    public static List<Schedule> getAllScheduleByDate(Object dateRange) {
        System.out.println("모든 멤버의 스케쥴을 dateRange 필터링에 따라 보여준다.");
    }
}
