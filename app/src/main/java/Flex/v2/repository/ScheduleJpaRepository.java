package Flex.v2.repository;

import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findScheduleByMember(Member member);

    Schedule findScheduleByMemberAndDate(Member member, LocalDate now);

    List<Schedule> findScheduleByMemberAndDateBetween(Member member, LocalDate from, LocalDate to);

}
