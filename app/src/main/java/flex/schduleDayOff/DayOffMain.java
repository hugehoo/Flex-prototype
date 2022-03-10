package flex.schduleDayOff;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DayOffMain {

    long userId = 1L;
    Date date = new Date();
    User user1 = new User(userId);
    Schedule scheduleOfUser1 = new Schedule(user1);


    // 주간 스케쥴을 불러온 후, 원하는 날짜에 휴가를 신청합니다.
    public void executeDayOff() {
        List<Schedule> weeklySchedule = scheduleOfUser1.getSchedule(userId, date);

        log.info(weeklySchedule.get(0).getTodaySchedule());

        weeklySchedule.get(3).setTodaySchedule("연차");//
        log.info("휴가를 사용했습니다.");

        weeklySchedule.get(4).setTodaySchedule("오전 반차");//
        log.info("휴가를 사용했습니다.");
        log.info("{} 날짜엔  {} 를 사용했습니다.", weeklySchedule.get(4),
            weeklySchedule.get(4).getTodaySchedule());

        scheduleOfUser1.saveSchedule(weeklySchedule);
    }

    // 신청한 휴가 내역을 확인합니다.
    public void executeDayOffCheck() {
        List<Schedule> userDayOff = scheduleOfUser1.showAllDayOff(userId);

        List<Schedule> pastDayOff = userDayOff.stream()
            .filter(dayOff -> date.before(dayOff.getDate()))
            .collect(Collectors.toList());

        List<Schedule> scheduledDayOff = userDayOff.stream()
            .filter(dayOff -> date.after(dayOff.getDate()))
            .collect(Collectors.toList());

        log.info("{} - {}", "과거 휴가내역", pastDayOff);
        log.info("{} - {}", "예정된 휴가내역", scheduledDayOff);
    }

}
