package Flex.v2.repository;

import Flex.v2.domain.Company;
import Flex.v2.domain.Member;
import Flex.v2.domain.WorkPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberJpaRepository memberRepository;

    @Autowired
    WorkPolicyRepository workPolicyRepository;

    @Autowired
    CompanyRepository companyRepository;

    private final static float companyInitLeave = 15;

    @Test
    @DisplayName("DB 에 미리 세팅된 데이터를 불러와 검증한다.")
    void findOne() {
        Optional<Member> member1 = memberRepository.findById(2L);
        member1.ifPresent(member -> assertEquals(2L, member.getId()));
    }

    @Test
    @Rollback
    @DisplayName("새로운 멤버 추가 후 모든 데이터를 불러오면, 이전보다 사이즈가 1 추가 된다.")
    void findAll() {

        // given
        String newMemberName = "한승윤";
        Member newMember = createMember(newMemberName);
        int beforeSize = memberRepository.findAll().size();

        // when
        memberRepository.save(newMember);
        int afterSize = memberRepository.findAll().size();

        // then
        assertEquals(afterSize, beforeSize + 1);
    }

    private Member createMember(String name) {
        return Member.builder()
                .name(name)
                .leaveCount(companyInitLeave)
                .build();
    }


    @Test
    @Transactional
    public void saveWorkPolicy() {

        LocalTime startTime = LocalTime.of(9, 0, 0);
        LocalTime endTime = LocalTime.of(18, 0, 0);

        LocalTime startTime10 = LocalTime.of(10, 0, 0);
        LocalTime endTime10 = LocalTime.of(19, 0, 0);

        WorkPolicy sample = WorkPolicy.builder()
                .name("고정출퇴근제")
                .startTime(startTime)
                .endTime(endTime)
                .basicWorkPolicy(true)
                .build();

        workPolicyRepository.save(sample);

        WorkPolicy sample2 = WorkPolicy.builder()
                .name("10시 출퇴근제")
                .startTime(startTime10)
                .endTime(endTime10)
                .build();

        workPolicyRepository.save(sample2);
    }


    @Test
    @DisplayName("Member 를 생성 후 저장, ")
    void createNewMemberAndCheckCompany() {

        Member newMember = createMember("한승윤");

        Optional<Company> companyOptional = companyRepository.findById(1L);
        companyOptional.ifPresent(newMember::setCompany);
        memberRepository.save(newMember);

        Optional<Member> memberOptional = memberRepository.findById(52L);
        Member member = memberOptional.orElse(new Member());
        assertEquals(1, member.getCompany().getId());
    }

}
