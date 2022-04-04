package Flex.v2.domain;


import Flex.v2.exception.NotEnoughLeaveException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private ScheduleStatus scheduleStatus;

    @Column(name = "schedule_workhour")
    private float workHour;

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
                .scheduleStatus(ScheduleStatus.DAY_OFF)
                .weekend(false)
                .workHour(Schedule.noWorkHour)
                .build();
    }

    public Schedule setHalfDayOff() {
        minusLeaveCount(halfDayOff); // new
        return Schedule.builder()
                .member(member)
                .date(date)
                .scheduleStatus(ScheduleStatus.HALF_DAY_OFF)
                .weekend(false)
                .workHour(Schedule.halfWorkHour)
                .build();
    }

    public void minusLeaveCount(float count) throws NotEnoughLeaveException {
        float remainLeaveCount = member.getLeaveCount() - count;
        if (remainLeaveCount < 0) {
            throw new NotEnoughLeaveException("잔여 휴가가 부족합니다.");
        }
        member.setLeaveCount(remainLeaveCount);
    }
}
