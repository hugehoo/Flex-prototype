package Flex.v2.domain;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;


@Deprecated
//@Entity
//@Table(name = "basic_work_policy")
public class BasicWorkPolicy {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "workpolicy")
    private Map<Long, WorkPolicy> workPolicy;

    @Column(name = "register_date")
    private LocalDate registerDate;

}
