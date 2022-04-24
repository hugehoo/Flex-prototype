package Flex.v2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "members")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<Schedule> scheduleList = new ArrayList<>();

    @Builder.Default
    @Column(name = "leave_days")
    private float leaveCount = 10;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

}
