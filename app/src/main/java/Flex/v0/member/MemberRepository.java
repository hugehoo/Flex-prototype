package Flex.v0.member;

import java.util.List;

public interface MemberRepository {

    List<Member> allMembers();

    List<Member> findByQuery(String query);
}
