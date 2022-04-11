package Flex.v2.repository;

import Flex.v2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

//    Optional<Member> findById(Long id);

}
