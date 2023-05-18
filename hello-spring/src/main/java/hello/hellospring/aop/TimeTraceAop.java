package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class TimeTraceAop {
     @Pointcut("execution(* hello.hellospring.controller..*.*(..))")
     private void cut() {}
     
     //사용자 지정 어노테이션이 붙은 메서드에도 적용!
     @Pointcut ("@annotation(hello.hellospring.annotation.Timer)")
     private void enableTimer() {}
     
//     @Around("execution(* hello.hellospring..*(..))")
     //메서드 전 후로 시간 측정 시작하고 멈추려면 Before, AfterReturning으로는 시간을 공유 할 수가 없음 Around 사용!
     @Around("cut() && enableTimer()")
     public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
          //메서드 시작 전
          StopWatch stopWatch = new StopWatch();
          stopWatch.start();
          
          //메서드가 실행되는 지점
          Object result = joinPoint.proceed();
          
          //메서드 종료 후
          stopWatch.stop();
          System.out.println("총 걸린 시간: " + stopWatch.getTotalTimeSeconds());
          
          return result;
     }
     
     public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
          long start = System.currentTimeMillis();
          System.out.println("Start: " + joinPoint.toString());
          try {
               return joinPoint.proceed(); // 다음 메소드 진행
          } finally {
               long finish = System.currentTimeMillis();
               long timeMs = finish - start;
               System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
          }
     }
}
