package com.company.organizationchart;

import java.util.List;

/**
 * 조직도를 구성하는 개별 조직 클래스
 */
public class Organization {

    /**
     * 부모 조직
     */
    public Organization parentOrganization;

    /**
     * 현재 조직 바로 하위의 조직 리스트
     */
    public List<Organization> directChildOrganization;

    /**
     * 조직명
     */
    public String organizationName;

    /**
     * 현재 조직의 depth
     */
    public int currentDepth;

    /**
     * Organization 의 생성자. 해당 조직의 이름과 현재 depth, 상위 조직의 정보를 포함한다.
     * @param parentOrganization : 현재 조직의 상위 조직
     * @param organizationName : 현재 조직 명
     * @param currentDepth : 현재 조직의 depth 를 나타낸다.
     */
    public Organization(Organization parentOrganization, String organizationName,
        int currentDepth) {
        this.parentOrganization = parentOrganization;
        this.organizationName = organizationName;
        this.currentDepth = currentDepth;
    }

    /**
     * 현재 조직의 이름을 수정합니다.
     * @param organizationName : 수정 될 조직명
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

}