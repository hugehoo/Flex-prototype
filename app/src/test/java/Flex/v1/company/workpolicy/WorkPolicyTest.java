package Flex.v1.company.workpolicy;

import Flex.v1.company.schedule.Schedule;
import Flex.v1.company.schedule.ScheduleRepository;
import Flex.v1.company.user.Role;
import Flex.v1.company.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WorkPolicyTest {

    static WorkPolicy teleComute;
    WorkPolicyRepository workPolicyRepository;
    Map<Long, WorkPolicy> workPolicyMap;

    ScheduleRepository scheduleRepository = new ScheduleRepository();

    @BeforeAll
    static void Create_WorkPolicy() {
        teleComute = WorkPolicy
                .builder()
                .name("재택 근무")
                .workPolicyInfo("자택에서 근무합니다.")
                .notification("팀")
                .paid(true)
                .isNightWork(false).isWeekendWork(false)
                .approvalAdministrator(true)
                .notifyAdministrator(false)
                .build();
    }

    // 무엇을 테스트해야할까.
    // save policy -> db 에도 저장이 됐는지 확인
    // saveWorkPolicy() 메서드의 규약은?
    // -> 키값이 null 이면 안돼고, 키값을 id 로 받아야하지 않을까? 키 값이 중복되도 안돼
    // 두번째 인자는 자바가 강타입이라 알아서 잡힐 것이고.
    //

    @Test
    @DisplayName("생성된 근무정책을 근무정책Repository 에 저장한다.")
    void saveWorkPolicy() {

        workPolicyRepository = new WorkPolicyRepository();
        workPolicyRepository.saveWorkPolicy(1L, teleComute);
        workPolicyMap = workPolicyRepository.workPolicyRepo;

        assertEquals(workPolicyMap.get(1L), workPolicyRepository.getWorkPolicy(1L));
    }


    @Test
    @DisplayName("삭제된 근무정책을 조회하면 Null 을 반환한다.")
    void deleteWorkPolicy() {

        workPolicyRepository = new WorkPolicyRepository();
        workPolicyRepository.saveWorkPolicy(1L, teleComute);
        workPolicyRepository.deleteWorkPolicy(1L);
        assertNull(workPolicyRepository.getWorkPolicy(1L));
    }


    @Test
    @DisplayName("존재하지 않는 정책을 삭제하려고 하면 예외를 던진다.")
    void When_Key_Not_Existed_Throws_Exception() {

        assertThrows(NullPointerException.class, () ->
                workPolicyRepository.deleteWorkPolicy(null)
        );
    }


    @Test
    @DisplayName("근무정책 저장, 삭제시 key 값이 null 이면 예외를 던진다.")
    void When_Key_Null_Throw_Exception() {

        workPolicyRepository = new WorkPolicyRepository();
        assertThrows(NullPointerException.class, () ->
                workPolicyRepository.saveWorkPolicy(null, teleComute)
        );

        assertThrows(NullPointerException.class, () ->
                workPolicyRepository.deleteWorkPolicy(null)
        );

    }

    @Test
    @DisplayName("알림 서비스 테스트 - 개발 진행중")
    @Disabled
    void notifyAdministrator() {

        // test 하고자 하는 것
        // 사원이 workpolicy 를 사용할 때, notifyAdmin() 이 true 이면,
        // manager 에게 알람을 보낸다. 알람이 잘 가는지 테스트해본다.

        User user1 = User
                .builder()
                .id(1L)
                .name("김사원1")
                .role(Role.RESEARCHER)
                .build();

        User manager1 = User
                .builder()
                .id(1L)
                .name("김매니저1")
                .role(Role.MANAGER)
                .build();

//        Boolean teleCom // 이걸 User 가 사용할 수 있도록 User 클래스도 수정해야한다.
        Map<String, Schedule> user1Schedule = scheduleRepository.getUserSchedule(user1.getId());
        Schedule specificDateSchedule = user1Schedule.get("2022-04-05");

        if (specificDateSchedule.changeWorkPolicy(teleComute)) {
            // Send Signal
        }

    }

}
