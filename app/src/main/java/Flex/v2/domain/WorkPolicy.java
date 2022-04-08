package Flex.v2.domain;

import javax.persistence.*;

@Entity
@Table(name = "workpolicy")
public class WorkPolicy {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "workpolicy_name")
    private String name;


}
