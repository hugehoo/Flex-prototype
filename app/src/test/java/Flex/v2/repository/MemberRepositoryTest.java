package Flex.v2.repository;

import Flex.v2.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    private final static float companyInitLeave = 15;

    @Test
    @Rollback(value = true) // false 로 인해 rollback 이 되지 않고, mysql 에 그대로 저장됨.
    @DisplayName("save() 의 인자가 null 일 때 NPE 를 던진다")
    void When_save_Method_Argument_Null_Throws_NPE() {
        Member member1 = null;
        assertThrows(NullPointerException.class, () -> {
            memberRepository.save(member1);
        });
    }

    @Test
    @DisplayName("findOne() 메서드의 인자가 null 이면 NullPointerException 을 던진다.")
    void When_findOne_Argument_Null_Throws_NPE() {
        assertThrows(NullPointerException.class, () -> {
            memberRepository.findOne(null);
        });
    }

    @Test
    @DisplayName("DB 에 미리 세팅된 데이터를 불러와 검증한다.")
    void findOne() {
        Member member1 = memberRepository.findOne(1L);
        assertEquals(1L, member1.getId());
        assertEquals("임성후", member1.getName());
        assertEquals(15, member1.getLeaveCount());
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
}