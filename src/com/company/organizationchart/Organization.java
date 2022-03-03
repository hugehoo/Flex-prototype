package com.company.organizationchart;

import java.util.List;

public class Organization {

    public Organization parentOrganization;

    public List<Organization> directChildOrganization;

    public String organizationName;

    public int currentDepth;

    public Organization(Organization parentOrganization, String organizationName,
        int currentDepth) {
        this.parentOrganization = parentOrganization;
        this.organizationName = organizationName;
        this.currentDepth = currentDepth;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

}