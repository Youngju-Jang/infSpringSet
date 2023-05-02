package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
     
     @Autowired
     MemberService memberService;
     @Autowired
     MemberRepository memberRepository;
     
//     @Commit //db에 반영시킴
     @Test
     void 회원가입() {
          // Given
          Member member = new Member();
          member.setName("hello");
          
          // When
          Long saveId = memberService.join(member);
          
          // Then
          Member findMember = memberService.findOne(saveId).get();
          assertThat(member.getName()).isEqualTo(findMember.getName());
     }
     
     @Test
     public void 중복_회원_예외() {
          // Given
          Member member1 = new Member();
          member1.setName("spring3");
          
          Member member2 = new Member();
          member2.setName("spring3");
          
          // When
          memberService.join(member1);
          IllegalStateException e = assertThrows(IllegalStateException.class,
               () -> memberService.join(member2));// 오른쪽을 실행하면 왼쪽예외 터짐
          
          // Then
          assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//          assertThrows(NullPointerException.class, () -> memberService.join(member2)); //

//          try {
//               memberService.join(member2);
//               fail();
//          } catch (IllegalStateException e) {
//               assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//          }
     }
}