package com.company.workingType;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class WorkingTypeMain {


    public static void main(String[] args) {
        WorkingType workingType = new WorkingType(
            "유연근무제",
            "고정",
            8,
            1,
            new int[]{7, 10},
            new String[]{"월"},
            new String[]{"일"}
        );

        System.out.println(workingType);
    }
}
//    String nameWorkingType; // 근무유형명
//    String workManagement; // 근무 관리 방식
//    int workingHour; // 일 근무시간
//    int breakTime; // 일 휴게 시간
//    int[] startWork; // 출근 가능 시간
//    String[] workingDays; // 일하는 날
//    String[] paidHolidays; // 유급 휴일
