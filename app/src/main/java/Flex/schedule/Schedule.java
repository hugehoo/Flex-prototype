package Flex.schedule;

import java.util.Date;

class Schedule {

    ScheduleRepository repository;


    public Schedule setDailyWorkType(Date date, WorkType workType, String workingHour, Boolean dayoff) {

        return repository.save(date, workType, workingHour, dayoff);
    }

}
