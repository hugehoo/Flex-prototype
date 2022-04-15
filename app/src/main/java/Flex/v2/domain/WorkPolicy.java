package Flex.v2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workpolicy")
public class WorkPolicy {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "workpolicy_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workPolicy_id")
    private Company company;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Builder.Default
    @Column(name = "basic_workpoilcy")
    private Boolean basicWorkPolicy = false;

}
