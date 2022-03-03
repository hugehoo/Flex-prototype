package com.company.organizationchart;


public interface OrganizationRepository {

    void saveOrganization(Organization organization);

    void deleteOrganization(Organization organization);
}