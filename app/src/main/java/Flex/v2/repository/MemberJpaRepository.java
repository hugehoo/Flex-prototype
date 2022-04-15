package Flex.v2.repository;

import Flex.v2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    @Query(value = "select m from Member m  join fetch m.company where m.id =:id")
    Member findMemberByIdWithCompany(@Param("id") Long id);

}
