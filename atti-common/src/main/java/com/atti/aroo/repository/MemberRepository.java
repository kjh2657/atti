package com.atti.aroo.repository;

import com.atti.aroo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);

    Member findMemberByName(String name);

    //Member findByName(String name);

}
