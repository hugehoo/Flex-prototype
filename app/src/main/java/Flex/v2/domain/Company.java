package Flex.v2.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String companyName;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Map<Long, Member> Member = new HashMap<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "workPolicy_id")// 수정 필요
    private static List<WorkPolicy> workPolicyList= new ArrayList<>();

    public static Company createCompany(String companyName, List<WorkPolicy> workPolicies) {
        Company company = Company.builder()
                .companyName(companyName)
                .build();
        workPolicyList.addAll(workPolicies);
        return company;
    }

    public void addWorkPolicy(WorkPolicy workPolicy) {
        workPolicyList.add(workPolicy);
        workPolicy.setCompany(this);
    }

}
