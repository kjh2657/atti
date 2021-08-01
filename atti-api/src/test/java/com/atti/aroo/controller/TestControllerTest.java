package com.atti.aroo.controller;

import com.atti.aroo.repository.MemberRepository;
import com.atti.aroo.support.aop.TimerAop;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberRepository memberRepository;

    @DisplayName("1. test")
    @Test
    void test_1() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        mockMvc.perform(MockMvcRequestBuilders.get("/timer"))
                .andExpect(status().isOk());
        stopWatch.stop();

        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        System.out.println("totalTimeMillis = " + totalTimeMillis);
        assertTrue(totalTimeMillis + " > 2000", totalTimeMillis > 2000 );
    }
}