package com.atti.aroo.repository;


import com.atti.aroo.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private MemberRepository memberRepository;


    @DisplayName("1. save")
    @Test
    void test_1(){

        Member member = givenMember();

        assertEquals("aroo", member.getName());
    }

    @DisplayName("2. get")
    @Test
    void test_2(){
        givenMember();

        Member aroo = memberRepository.findByName("aroo");

        assertEquals("aroo", aroo.getName());
    }


    public Member givenMember(){
        Member member = new Member();
        member.setName("aroo");

        Member save = memberRepository.save(member);

        return save;
    }
}
