package flex.schedule;

import java.util.Date;

public class ScheduleMain {

    class Company {
        // TBD
    }

    ScheduleRepository repository;
    Company company;
    User user;
    String weekly;
    Date date1;
    Date date2;
    WorkType ordinaryWork;
    WorkType houseWork;
    Boolean dayoff;
    String workingHour;

    void execute() {
        // 1. 개인 근무 유형 확인한다.
        WorkType companyWorkType = new WorkType(company);
        WorkType personalWorkType = companyWorkType.getPersonalWorkType(user);

        // 2. 개인 근무 유형과 해당 주 활용하여 주간 스케쥴 리턴받는다.
        Schedule weeklySchedule = repository.getWeeklySchedule(personalWorkType, weekly);

        // 3.주간 스케쥴에서 특정 날짜의 근무유형을 정한다.
        weeklySchedule.setDailyWorkType(date1, ordinaryWork, workingHour, dayoff);

        // 3-1. 특정 날짜의 스케쥴은 재택근무를 한다.
        weeklySchedule.setDailyWorkType(date2, houseWork, workingHour, dayoff);
    }

}
