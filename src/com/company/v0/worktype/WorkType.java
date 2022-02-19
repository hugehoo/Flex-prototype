package com.company.v0.worktype;

import com.sun.org.apache.xpath.internal.operations.Bool;
import java.util.ArrayList;
import java.util.Arrays;

public class WorkType {

    String workTypeName;
    String workManageType; //  근무 관리 방식 (고정, 시차, 선택적, 스케쥴 중 선택)
    int workHour; // 일 근무시간
    int[] startHourRange;
    String[] workingDay;
    String[] paidHoliday;
    int weeklyWorkingHours;

    public WorkType(String workTypeName, String workManageType, int workHour, int[] startHourRange,
        String[] workingDay, String[] paidHoliday) {
        this.workTypeName = workTypeName;
        this.workManageType = workManageType;
        this.workHour = workHour;
        this.startHourRange = startHourRange;
        this.workingDay = workingDay;
        this.paidHoliday = paidHoliday;
    }

    public void saveNewWorkType() {
        saveWeeklyWorkingHours();
        System.out.println("save new work hours");
    }

    public void saveWeeklyWorkingHours() {
        this.weeklyWorkingHours = this.workHour * this.workingDay.length;
    }
}
