package com.company.v0.dayoff;

import java.util.HashMap;

public class DayOffMain {

    public static void main(String[] args) {
        // 현재 회사의 휴가정책을 먼저 보여준다.
        DayOff.showCurrentDayOffPolicy();

        String startDay = "20220223";
        String endDay = "20220225";
        DayOff dayOff = new DayOff(startDay, endDay);

        HashMap<String, String> dayOffs = new HashMap<String, String>(){{
            put("20220223", "연차");
            put("20220224", "연차");
            put("20220225", "오전반차");
        }};
        dayOff.setEachDayDayOff(dayOffs);

        dayOff.save();
    }


}
