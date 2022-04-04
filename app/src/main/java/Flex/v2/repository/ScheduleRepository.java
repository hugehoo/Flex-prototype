package Flex.v2.repository;


import Flex.v2.domain.Member;
import Flex.v2.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    @PersistenceContext
    private final EntityManager em;

    public Long save(Schedule schedule) {
        em.persist(schedule);
        return schedule.getId();
    }

    public Schedule findById(Long scheduleId) {
        return em.find(Schedule.class, scheduleId);
    }

    public List<Schedule> findByMember(Member member) {
        return em.createQuery("select o from Schedule o join o.member m where o.member = :memberId", Schedule.class)
                .setParameter("memberId", member)
                .getResultList();
    }
}
