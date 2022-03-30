package Flex.v1.company.leave;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaveTest {


    @Test
    void createLeave() {
        // 회사단위에서 휴가를 만들어내는 것.
        Leave leave = Leave.builder()
                .name("리프레시 휴가")
                .leaveUnit(LeaveUnit.DAY)
                .build();

        System.out.println(leave);
        System.out.println(leave.getLeaveDay());
    }

    @Test
    @DisplayName("회사에 미리 저장된 기본 휴가 목록을 가져옵니다.")
    void getLeave() {

    }

    @Test
    void saveLeave() {
    }

    @Test
    @DisplayName("법정 공휴일을 가져옵니다. 이건 모든 회사에 공통적으로 저장되거나, 공유 메모리에 올리거나. ")
    void getLegalOfficialLeave() {
    }
}