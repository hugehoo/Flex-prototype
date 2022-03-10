package Flex.v0.schduleDayOff;

import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;


/**
 *  일간 스케쥴을 의미하는 클래스입니다.
 *  날짜와, 그 날에 해당하는 근무 유형(todaySchedule)과 근무 시간을 나타냅니다.
 *  Schedule 클래스는 ScheduleRepository 를 사용하므로, 의존성을 가집니다.
 */

@Slf4j
public class Schedule {

    ScheduleRepository scheduleRepository;

    public User user;
    public Date date;
    public String todaySchedule; // 근무, 외근, 연차
    public String workingHour;

    public Schedule(User user) {
        this.user = user;
    }

    public List<Schedule> getSchedule(long userId, Date date) {
        log.info("ScheduleRepository 에서 유저의 주간 스케쥴을 가져옵니다.");
        return scheduleRepository.getPersonalWeeklySchedule(userId, date);
    }

    public void saveSchedule(List<Schedule> schedules) {
        log.info("스케쥴을 저장합니다.");
        scheduleRepository.save(schedules);
    }

    public List<Schedule> showAllDayOff(long userId) {
        log.info("해당 유저의 모든 스케쥴을 보여줍니다.");
        return scheduleRepository.getAllDayOffSchedule(userId);
    }

    public Date getDate() {
        return date;
    }

    public String getTodaySchedule() {
        return todaySchedule;
    }

    public void setTodaySchedule(String todaySchedule) {
        this.todaySchedule = todaySchedule;
    }

    public String getWorkingHour() {
        return workingHour;
    }

    public void setDayOff(String dayOff) {
        this.todaySchedule = dayOff;
    }

    public void setWorkingHour(String workingHour) {
        this.workingHour = workingHour;
    }
}