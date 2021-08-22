package com.atti.aroo.controller;

import com.atti.aroo.dto.MemberDto;
import com.atti.aroo.entity.Member;
import com.atti.aroo.repository.MemberRepository;
import com.atti.aroo.support.annotation.Timer;
import com.atti.aroo.support.dto.Error;
import com.atti.aroo.support.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Api(tags = "Test API Controller")
@Slf4j
@RestController
public class TestController {


    private final MemberRepository memberRepository;

    public TestController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @ApiOperation(value = "Test Get")
    @GetMapping("/api/test")
    public String hello(){
        return "hello";
    }

    @ApiOperation(value = "Test Get")
    @GetMapping("/test1")
    public String hello1(){
        return "hello1";
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
        int age = member.getAge();

        return ResponseEntity.status(HttpStatus.OK).body(member);
    }


    @ApiOperation(value = "ArithException", notes = "Arithmatics Exception 발생")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "a", value = "a / b의 a 값", required = true, dataType = "int" , paramType = "query"),
            @ApiImplicitParam(name = "b", value = "a / b의 b 값", required = true, dataType = "int" , paramType = "query")
    })
    @GetMapping("/math")
    public int mathException(@RequestParam int a, @RequestParam int b){
        return a/b;
    }

    @GetMapping("/null")
    public void nullException(){
        throw new NullPointerException("nullllll");
    }


    @GetMapping("/missing")
    public int missException(@RequestParam int a, @RequestParam int b){

        int c = 0 ;
        c += a;
        c += b;

        return c;
    }

    @ApiOperation(value = "constraint Exception", notes = "constraint Exception 발생")
    @PostMapping("/constr")
    public void constr(@RequestBody MemberDto member){
        Member member1 = new Member();
        member1.setName(member.getName());
        member1.setUserId(member1.getUserId());
        memberRepository.save(member1);
    }

    @GetMapping("/attiex")
    public void attiEx(){

        throw new CustomException("atti error");
    }
}
