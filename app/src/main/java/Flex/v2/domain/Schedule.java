package Flex.v2.domain;


import Flex.v2.exception.NotEnoughLeaveException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "schedules")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "schedule_date")
    private LocalDate date;

    @Column(name = "weekend")
    private boolean weekend;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private ScheduleStatus scheduleStatus;

    @Column(name = "schedule_workhour")
    private float workHour;

    @Builder.Default
    @Column(name = "start_time")
    private LocalTime startTime = LocalTime.of(9, 0, 0);

    @Builder.Default
    @Column(name = "end_time")
    private LocalTime endTime = LocalTime.of(18, 0, 0);

    @Builder.Default
    public static float basicWorkHour = 8F;
    public static float noWorkHour = 0F;
    public static float halfWorkHour = 4F;

    // 여기서 scheduleStatus 를 세팅한다 -> 근무, 연차, 외근 등

    public static Schedule setBasicSchedule(Member member, LocalDate date) {
        return Schedule.builder()
                .date(date)
                .scheduleStatus(ScheduleStatus.WORK)
                .weekend(false)
                .member(member)
                .workHour(Schedule.basicWorkHour)
                .build();
    }

    public static Schedule setWeekendSchedule(Member member, LocalDate date) {
        return Schedule.builder()
                .date(date)
                .startTime(null)
                .endTime(null)
                .scheduleStatus(ScheduleStatus.WEEKEND)
                .weekend(true)
                .member(member)
                .workHour(Schedule.noWorkHour)
                .build();
    }

    public void setTelecommute() {
        this.scheduleStatus = ScheduleStatus.TELECOMMUTE;
    }

    private static float dayOff = 1F;
    private static float halfDayOff = 0.5F;

    public Schedule setDayOff() {
        minusLeaveCount(dayOff); // new
        return Schedule.builder()
                .member(member)
                .date(date)
                .startTime(null)
                .endTime(null)
                .scheduleStatus(ScheduleStatus.DAY_OFF)
                .weekend(false)
                .workHour(Schedule.noWorkHour)
                .build();
    }

    public Schedule setHalfDayOff(ScheduleStatus halfDayOffStatus) {

        Map<String, LocalTime> resultMap = decideHalfDayOff(halfDayOffStatus);
        minusLeaveCount(halfDayOff);

        return Schedule.builder()
                .member(member)
                .date(date)
                .startTime(resultMap.get("start"))
                .endTime(resultMap.get("end"))
                .scheduleStatus(halfDayOffStatus)
                .weekend(false)
                .workHour(Schedule.halfWorkHour)
                .build();
    }

    public Map<String, LocalTime > decideHalfDayOff(ScheduleStatus halfDayOffStatus) {

        Map<String, LocalTime> resultMap = new HashMap<>();

        LocalTime startTime = halfDayOffStatus == ScheduleStatus.HALF_DAY_OFF_MORNING ?
                LocalTime.of(14, 0, 0) : LocalTime.of(9, 0, 0);
        LocalTime endTime = halfDayOffStatus == ScheduleStatus.HALF_DAY_OFF_MORNING ?
                LocalTime.of(18, 0, 0) : LocalTime.of(14, 0, 0);

        resultMap.put("start", startTime);
        resultMap.put("end", endTime);

        return resultMap;
    }

    public void minusLeaveCount(float count) throws NotEnoughLeaveException {
        float remainLeaveCount = member.getLeaveCount() - count;
        if (remainLeaveCount < 0) {
            throw new NotEnoughLeaveException("잔여 휴가가 부족합니다.");
        }
        member.setLeaveCount(remainLeaveCount);
    }
}
