package Flex.v2.repository;

import Flex.v2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberJpaRepository extends JpaRepository<Member, Long> {

}
