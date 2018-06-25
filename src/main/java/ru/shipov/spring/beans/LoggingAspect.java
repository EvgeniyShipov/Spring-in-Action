package ru.shipov.spring.beans;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class LoggingAspect {
    private final Map<Class<?>, Integer> statistics = new HashMap<>();

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethords() {

    }

    @Before("allLogEventMethords()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE : " + joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "allLogEventMethords()")
    public void logAfter(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        statistics.put(clazz, statistics.getOrDefault(clazz, 0) + 1);
    }

    @AfterReturning(
            pointcut = "allLogEventMethords()",
            returning = "result")
    public void logAfter(Object result) {
        System.out.println("Returned value: " + result);
    }

    @AfterThrowing(
            pointcut = "allLogEventMethords()",
            throwing = "exception")
    public void logAfterExeption(Throwable exception) {
        System.out.println("Method thrown: " + exception);
    }

    @Around("allLogEventMethords() && args(event)")
    public void aroubdLogEvent(ProceedingJoinPoint joinPoint, Object event) throws Throwable {
        Class<?> clazz = joinPoint.getTarget().getClass();
        if (statistics.getOrDefault(clazz, 0) > 5) {
            joinPoint.proceed(new Object[]{event});
        } else {
            System.out.println("Logger is full");
        }
    }
}
