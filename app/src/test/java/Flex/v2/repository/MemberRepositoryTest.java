package Flex.v2.repository;

import Flex.v2.domain.Company;
import Flex.v2.domain.Member;
import Flex.v2.domain.WorkPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
    @Rollback(value = true) // false 로 인해 rollback 이 되지 않고, mysql 에 그대로 저장됨.
    @DisplayName("save() 의 인자가 null 일 때 NPE 를 던진다")
    void When_save_Method_Argument_Null_Throws_NPE() {
        Member member1 = null;
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            memberRepository.save(member1);
        });
    }

    @Test
    @DisplayName("findOne() 메서드의 인자가 null 이면 NullPointerException 을 던진다.")
    void When_findOne_Argument_Null_Throws_NPE() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            memberRepository.findOne(null);
        });
    }

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
    @Rollback(value = false)
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
    @DisplayName("Error : 새로운 멤버를 추가하고, 소속 회사를 지정한다. -> could not initialize proxy error")
    void createNewMember() {

        String memberName = "한승윤";
        Member newMember = createMember(memberName);

//        Company companyProxyError = companyRepository.getById(1L); // 이거 사용시 proxyError 발생 // findById() 와 getById() 차이가 뭐여
//        System.out.println(companyProxyError);

        Optional<Company> company = companyRepository.findById(1L);
        company.ifPresent(newMember::setCompany);
        memberRepository.save(newMember);

        Optional<Member> member = memberRepository.findById(2L);
//        Optional<Member> member = memberRepository.findById(2L);
        member.ifPresent(member1 -> System.out.println(member1.getId()));
        member.ifPresent(member1 -> System.out.println(member1.getName()));
        // member.ifPresent(member1 -> System.out.println(member1.getCompany())); // proxy error 발생
    }

}
