package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // 컨테이너가 인식하고 controller객체를 생성해서 넣어둠(bean 관리)
public class MemberController {
     
     // 이렇게 할 경우, 다른 컨트롤러에서 서비스를 쓸 때, 여러 인스턴스를 생성하게 됨
//     private final MemberService memberService = new MemberService();
     
     // 1.필드주입방식 : 바꿀 방법이 없음. 비추
//     @Autowired private MemberService memberService;
     
     // 2. setter 주입 : public으로 열려있어야 함. 노출이 되어버려. 잘못바뀌면 문제됨(보통 한번셋팅후 볼일없음)
     private MemberService memberService;
//     @Autowired
//     public void setMemberService(MemberService memberService){
//          this.memberService = memberService;
//     }
     // 3. 생성자 주입방식 : 생성자 주입시점에 넣고 변경을 막아줌
     @Autowired
     public MemberController(MemberService memberService) { //DI . bean컨테이너가 관리하도록. //Component scan방식
          this.memberService = memberService;
     }
}
