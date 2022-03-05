package Flex.member;

import java.util.List;
import java.util.Map;

public class MemberMain {

    /**
     * 1. 회사의 전체 멤버를 조회할 수 있다.
     * 1. 전체 조직을 부서별로 분류하여 조회할 수 있다.
     * 1. 이름, 부서, 역할로 검색할 수 있다.
     * 1. 근무 상태별(휴직, 재직, 휴가, 퇴직) 로 조회할 수 있다.
     */

    MemberRepository memberRepository;
    String word = "백엔드";
    String status = "휴직";
    Company company = new Company();


    public void execute() {

        /**
         * MemberRepository 에서 회사의 전체 멤버를 조회합니다.
         */
        List<Member> allMembers = memberRepository.allMembers();

        /**
         * 부서별 인원을 Map 형태로 조회합니다.
         */
        Map<String, List<Member>> teamMembers = company.showMembersByTeam(allMembers);

        /**
         * 근무 상태 별로 멤버를 조회합니다.
          */
        List<Member> members = company.showMemberByStatus(allMembers, status);

        /**
         * 검색어(이름, 부서, 역할) 에 해당되는 멤버를 조회합니다.
         */
        List<Member> membersByQuery = memberRepository.findByQuery(word);

    }

}
