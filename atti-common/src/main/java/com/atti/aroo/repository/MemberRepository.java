package com.atti.aroo.repository;

import com.atti.aroo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);

    Member findOneByName(String name);

    List<Optional<Member>> findMemberByName(String name);
    //Optional<Member> findMemberByName(String name);

    //Member findByName(String name);

}
