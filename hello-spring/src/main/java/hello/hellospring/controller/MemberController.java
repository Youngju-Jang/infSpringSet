package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 컨테이너가 인식하고 controller객체를 생성해서 넣어둠(bean 관리)
public class MemberController {
     
     // 이렇게 할 경우, 다른 컨트롤러에서 서비스를 쓸 때, 여러 인스턴스를 생성하게 됨
//     private final MemberService memberService = new MemberService();
     
     private final MemberService membrService;
     
     @Autowired
     public MemberController(MemberService membrService) { //DI . bean컨테이너가 관리하도록. //Component scan방식
          this.membrService = membrService;
     }
}
