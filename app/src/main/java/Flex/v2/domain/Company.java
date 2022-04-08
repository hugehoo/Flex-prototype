package Flex.v2.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Entity
@Getter
@Setter
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    private String companyName;

    @OneToMany
    private Map<Long, Member> Member = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "basic_workpolicy")// 수정 필요
    private BasicWorkPolicy basicWorkPolicy;


}
