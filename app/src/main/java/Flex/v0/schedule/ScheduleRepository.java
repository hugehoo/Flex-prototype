package Flex.v0.schedule;

import java.util.Date;

interface ScheduleRepository {

    Schedule getWeeklySchedule(WorkType workType, String weekly);

    Schedule save(Date date, WorkType workType, String workingHour, Boolean dayoff);
}