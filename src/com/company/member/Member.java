package com.company.member;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Member Class
 * 회사의 구성원은 나타내는 클래스로, name, team, role, status 같은 상태를 가집니다.
 */
public class Member {

    private String name;
    private String team;
    private String role;
    private String status;


    public Member(String name, String team, String role, String status) {
        this.name = name;
        this.team = team;
        this.role = role;
        this.status = status;
    }

    public String getTeam() {
        return team;
    }

    public String getStatus() {
        return status;
    }
}
