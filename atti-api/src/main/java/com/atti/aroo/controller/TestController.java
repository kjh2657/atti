package com.atti.aroo.controller;

import com.atti.aroo.dto.MemberDto;
import com.atti.aroo.entity.Member;
import com.atti.aroo.repository.MemberRepository;
import com.atti.aroo.support.annotation.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
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


//    @Timer
    @GetMapping("/save")
    public void setMember(){
        Member member = new Member();
        member.setName("aroo");
        memberRepository.save(member);
    }

//    @Timer
    @GetMapping("/common")
    public void getMember(){
        Member one = memberRepository.getOne(1L);
        System.out.println(one.toString());
    }

    @Timer
    @GetMapping("/timer")
    public void timer() throws InterruptedException {

        Thread.sleep(2000);
    }

    @Timer
    @GetMapping("/param")
    public String param(@RequestParam String a, @RequestParam int b){
        return a;
    }

    @Timer
    @PostMapping("/postparam")
    public ResponseEntity<MemberDto> postparam(@RequestBody MemberDto member){

        String name = member.getName();
        String age = member.getAge();

        return ResponseEntity.status(HttpStatus.OK).body(member);
    }
}
