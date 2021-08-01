package com.atti.aroo.support.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class TimerAop {

    @Around("@annotation(com.atti.aroo.support.annotation.Timer)")
    public Object showTime(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        String methodName = proceedingJoinPoint.getSignature().getName();

        stopWatch.stop();

        log.info("===== AttiAroo Aop Timer Method = {} Proceeding Time = {} millis" , methodName, stopWatch.getTotalTimeMillis());

        return result;
    }

    @Before("@annotation(com.atti.aroo.support.annotation.Timer)")
    public void showParams(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] paramValue = joinPoint.getArgs();

        log.info("===== AttiAroo Aop Param Method = {} param Value = {} " , methodName, paramValue);
    }

    @AfterReturning(value = "@annotation(com.atti.aroo.support.annotation.Timer)", returning = "returnValue")
    public void showResponse(JoinPoint joinPoint, Object returnValue){

        String methodName = joinPoint.getSignature().getName();

        if(returnValue instanceof ResponseEntity) {
            ResponseEntity responseEntity = (ResponseEntity) returnValue;
            log.info("===== AttiAroo Aop Response Method = {} Response Status = {}  Response Value = {} ", methodName, responseEntity.getStatusCode(), responseEntity.getBody());
        } else {
            log.info("===== AttiAroo Aop Response Method = {}  Response Value = {} ", methodName, returnValue.toString());
        }
    }

}
