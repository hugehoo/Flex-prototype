package Flex.v2.repository;

import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleJpaRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findScheduleByMember(Member member);


}
