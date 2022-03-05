package Flex.member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Company Class
 * 회사의 부서, 부서별 인원 조회와 인원의 재직 상태별 조회 기능을 가집니다.
 */
public class Company {

    // 회사의 부서
    public static List<String> teams = Arrays.asList("마케팅팀", "백엔드팀", "피플팀", "안드로이드팀");

    // 전체 부서별 인원 조회
    public Map<String, List<Member>> showMembersByTeam(List<Member> allMembers) {
        Map<String, List<Member>> teamMembers = new HashMap<>();

        for (String team : teams) {
            teamMembers.put(team, new ArrayList<>());
        }
        allMembers.forEach(member ->
            teamMembers.get(member.getTeam()).add(member)
        );
        return teamMembers;
    }

    // 특정 재직 상태의 인원 조회
    public List<Member> showMemberByStatus(List<Member> allMembers, String status) {
        return allMembers.stream()
            .filter(member -> status.equals(member.getStatus()))
            .collect(Collectors.toList());
    }
}
