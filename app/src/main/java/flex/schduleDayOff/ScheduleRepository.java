package flex.schduleDayOff;

import java.util.Date;
import java.util.List;

interface ScheduleRepository {

    List<Schedule> getPersonalWeeklySchedule(long userId, Date date);

    void save(List<Schedule> schedules);

    List<Schedule> getAllDayOffSchedule(long userId);

}