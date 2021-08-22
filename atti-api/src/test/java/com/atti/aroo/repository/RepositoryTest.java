package com.atti.aroo.repository;


import com.atti.aroo.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("1. save")
    @Test
    void test_1() {

        Member member = givenMember("aroo");

        assertEquals("aroo", member.getName());
    }

    @DisplayName("2. get")
    @Test
    void test_2() {
        givenMember("aroo");

        Member aroo = memberRepository.findByName("aroo");

        assertEquals("aroo", aroo.getName());
    }

    @DisplayName("3. Optional")
    @Test
    void optional1() {
        Optional<Member> findMember;
        findMember = memberRepository.findById(1L);
    }

    @Test
    void optional() {
        givenMemberAndId("aroo", "ar");

        Optional<Member> findMember = memberRepository.findById(3L);

        //Member result = findMember.orElseGet(Member::new);
        //Member result = findMember.orElseThrow(CustomException::new);
        findMember.ifPresentOrElse(member -> {
            System.out.println("member = " + member);
        }, () -> {
            System.out.println("no value");
        });
    }


    @Test
    void stream() {

        givenMember("aroo");
        givenMember("atti");
        givenMember("bam");
        givenMember("pang");

        List<Member> list = memberRepository.findAll();


        list
                .stream()
                .sorted(Comparator.comparing(Member::getName).reversed())
                .forEach(System.out::println);

        list.stream().filter(s->s.getName().contains("a"))
                .forEach(System.out::println);
    }

    public Member givenMember(String name) {
        Member member = new Member();
        member.setName(name);

        Member save = memberRepository.save(member);


        return save;
    }

    public Member givenMemberAndId(String name, String userId) {
        Member member = new Member();
        member.setName(name);
        member.setUserId(userId);

        Member save = memberRepository.save(member);


        return save;
    }
}
