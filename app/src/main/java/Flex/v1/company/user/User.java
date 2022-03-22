package Flex.v1.company.user;

import Flex.v1.company.worktype.WorkType;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * flex 를 사용하는 유저를 나타내는 클래스. 어떤 속성과 메서드가 있어야할까. 지금 진행중인 모듈 기준으로 생각해보자 . 스케쥴, 휴가, 근무유형, 근무정책(근무정책
 * 조회,선택 의미. 수정은 안됨) 연차/반차, 당연히 이름, 등 개인정보도 필요함. 회원가입부터 관리자에의한 초대. 휴가 상신, 스케쥴 조회, 아직 로그인, 가입은 생각하지
 * 말자.이미 가입된 상태의 회원이 가질 수 있는 정보(속성)은 무엇일까
 */
@Getter
@Setter
@Builder
public class User {

    private String name;

    private long id;

    private String email;

    private String displayName;

    private Date birthDay;

    private Date createdDate;

    private boolean inactiveAccountStatus;

    // 휴가와 연차를 별개로 주는지도 생각해야함.
    private int leftLeaveDays;

    /**
     * 근무유형 | 정규직과 인턴의 근무유형이 다를 수 있듯이, 개인별로 적용되는 근무유형은 다를 수 있다.
     */
    private WorkType currentWorkType;

    /**
     * 소속 조직
     */
    private String departments;

    /**
     * 역할 (직무)
     */
    private String role;

    private Date dateOfEntry;

    /**
     * 확정된 연차 내역
     */
//    private ConfirmedTimeOffUse confirmedTimeOffUses;


    // 아직 ScheduleRepo가 merge 되지 않았다.

    /**
     * 기존의 근무유형과 다른 근무유형을 선택한다.
     * 근무유형을 변경하면, 스케쥴 클래스도 변동이 생길 것.
     */
    public void setPersonalWorkType(String date, WorkType workType) {

        // date 파라미터 를 기준으로 WorkType 이 변경된다.
        // 또한 date 기준 이후의 지정된 모든 일정들은 취소된다. -> schedule 을 초기화 하자.
        this.currentWorkType = workType;
    }


}
