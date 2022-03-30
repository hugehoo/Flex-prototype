package Flex.v1.company.leave;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * 개인별 휴가를 생성하는 클래스입니다. (day off package: 회사의 공통 휴일을 의미)
 */
@Getter
@Setter
@Builder
public class Leave {

    /**
     * 휴가 이름
     */
    @NonNull
    private String name;

    /**
     * 휴가 사용 단위 (일 / 반차 / 시간용 / 연속사용)
     */
    @NonNull
    private LeaveUnit leaveUnit;

    /**
     * 휴가 지급일
     */
    @Builder.Default
    private Integer leaveDay = 1;

    /**
     * 휴일 포함
     */
    @Builder.Default
    private boolean isIncludeHoliday = false;

    /**
     * 급여 지급
     */
    @NonNull
    @Builder.Default
    private Paid paidLeave = Paid.PAID;

    /**
     * 적용 성별
     */
    @NonNull
    private String sexualAdaption;

    /*
    조직장 승인 설정
     */
    private Boolean approvalAdministrator;

    /**
     * 조직장 알림 설정
     */
    private boolean notifyAdministrator;

}
