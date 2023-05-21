package hello.hellospring.aop;

import hello.hellospring.aop.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {
     
     @Pointcut ("execution(* hello.hellospring.controller..*.*(..))")
     private void cut() {}
     
     @Pointcut("@annotation(hello.hellospring.annotation.Decode)")
     private void enableDecode() {}
     
     //암호화된 데이터 들어오면 복호화!
     @Before ("cut() && enableDecode()")
     public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
          
          //메서드로 들어오는 매개변수들
          Object[] args = (Object[]) joinPoint.getArgs();
          
          for(Object arg : args) {
               if(arg instanceof User)  {
                    //클래스 캐스팅
                    User user = User.class.cast(arg);
                    String base64Email = user.getEmail();
                    //복호화
                    String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8");
                    user.setEmail(email);
               }
          }
     }
     
     //리턴할때는 암호화 하여 리턴
     @AfterReturning (value = "cut() && enableDecode()", returning = "returnObj")
     public void afterReturn(JoinPoint joinPoint, Object returnObj) {
          
          if(returnObj instanceof User) {
               //클래스 캐스팅
               User user = User.class.cast(returnObj);
               String email = user.getEmail();
               //암호화
               String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
               user.setEmail(base64Email);
          }
     }
}
