package com.company.v0.worktype;

public class WorkTypeMain {

    public void createNewWorkType() {
        // 처음엔 setter 를 사용하여 각 input 값을 넣어주려 했습니다.
//        WorkType workType = new WorkType();
//        workType.setWorkTypeName("주40시간");  // 근무 유형 이름
//        workType.setType("시차"); //  근무 관리 방식 (고정, 시차, 선택적, 스케쥴 중 선택)
//        workType.setDailyWorkHour("8"); // 일 근무시간
//        workType.setStartHoursRange("8", "11"); // 출근 가능한 시간 범위
//        workType.setWorkingDay("월화수목금"); // 일하는 날 설정
//        workType.setPaidHoliday("토일"); // 유급휴일 설정
//        workType.calculateWeekWorkingHours();  // 주간 근무시간 계산


        // 생성자에 각 input 값을 넣는 방식으로 변경하였습니다.
        // 근무 유형 생성
        WorkType workType = new WorkType(
            "주40시간",
            "시차",
            8,
            new int[]{8, 11},
            new String[]{"월", "화", "수", "목", "금"},
            new String[]{"토", "일"}
        );

        // 근무 유형 저장.
        workType.saveNewWorkType();
    }

}
