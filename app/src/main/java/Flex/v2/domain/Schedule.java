package Flex.v2.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

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

    public void setDayOff(Leave leave) {
        leave.useDayOff();
        this.scheduleStatus = ScheduleStatus.DAY_OFF;
        this.workHour = 0F;
    }

    public void setHalfDayOff(Leave leave) {
        leave.useHalfDayOff();
        this.scheduleStatus = ScheduleStatus.HALF_DAY_OFF;
        this.workHour = basicWorkHour / 2;
    }

}
