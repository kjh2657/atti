package com.atti.aroo.controller;

import com.atti.aroo.entity.Member;
import com.atti.aroo.repository.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    private final MemberRepository memberRepository;

    public TestController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/test")
    public String hello(){
        return "hello";
    }


    @GetMapping("/save")
    public void setMember(){
        Member member = new Member();
        member.setName("aroo");
        memberRepository.save(member);
    }

    @GetMapping("/common")
    public void getMember(){
        Member one = memberRepository.getOne(1L);
        System.out.println(one.toString());
    }
}
