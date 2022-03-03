package com.company.member;

import java.util.List;

public interface MemberRepository {

    List<Member> allMembers();

    List<Member> findByQuery(String query);
}
