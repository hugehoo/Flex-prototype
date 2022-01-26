package com.company.workingType;



public class WorkingType {

    String nameWorkingType; // 근무유형명
    String workManagement; // 근무 관리 방식

    int workingHour; // 일 근무시간
    int breakTime; // 일 휴게 시간

    int[] startWork; // 출근 가능 시간

    String[] workingDays; // 일하는 날
    String[] paidHolidays; // 유급 휴일

    public WorkingType(String nameWorkingType, String workManagement, int workingHour,
        int breakTime,
        int[] startWork, String[] workingDays, String[] paidHolidays) {
        this.nameWorkingType = nameWorkingType;
        this.workManagement = workManagement;
        this.workingHour = workingHour;
        this.breakTime = breakTime;
        this.startWork = startWork;
        this.workingDays = workingDays;
        this.paidHolidays = paidHolidays;
    }
}
