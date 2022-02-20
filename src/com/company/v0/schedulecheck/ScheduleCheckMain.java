package com.company.v0.schedulecheck;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleCheckMain {

    public List<Schedule> showMemberSchedule() {
        // 날짜 필터 -> 스케쥴 클래스를 모두 가져온다.(클래스에는 각 유저의 아이디와 이름이 있어야할듯..?NoSql 설계?) -> 이때 날짜 필터를 걸자.
        List<Schedule> schedules = Schedule.getAllScheduleByDate("dateRange");

        List<Schedule> finalScheduleList = schedules.stream()
            .filter(schedule -> filterWorkPolicy(schedule, new int[]{0, 1})) // 0, 1번째 필터
            .filter(schedule -> filterOrganization(schedule, new int[] {1}))
            .collect(Collectors.toList());
        return finalScheduleList;
    }

    private boolean filterOrganization(Schedule schedule, int[] selectedFilters) {
        String workPolicy = schedule.getWorkPolicy();
        return Arrays.asList(selectedFilters).contains(workPolicy);
    }

    public Boolean filterWorkPolicy(Schedule schedule, int[] selectedFilters) {

        String workPolicy = schedule.getWorkPolicy();
        return Arrays.asList(selectedFilters).contains(workPolicy);
    }

}
