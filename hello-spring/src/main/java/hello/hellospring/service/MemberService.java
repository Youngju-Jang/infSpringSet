package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
     
     private final MemberRepository memberRepository;
     
     public MemberService(MemberRepository memberRepository) {
          this.memberRepository = memberRepository; // test 에서도 같은 repo를 쓰기위해 생성자에서 받기(기존: 전역에서 객체생성했음)
     }
     
     // 회원가입
     public Long join(Member member) {
          // 같은 이름이 있는 중복회원 x
          validateDuplicateMember(member); // 중복 회원 검증
          memberRepository.save(member);
          return member.getId();
     }
     
     private void validateDuplicateMember(Member member) {
          memberRepository.findByName(member.getName())
               .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
               });
     }
     
     // 전체회원 조회
     public List<Member> findMembers(){
          return memberRepository.findAll();
     }
     
     public Optional<Member> findOne(Long memberId){
          return memberRepository.findById(memberId);
     }
}
