package hello.hellospring.controller;

import hello.hellospring.annotation.Decode;
import hello.hellospring.annotation.Timer;
import hello.hellospring.aop.model.User;
import hello.hellospring.domain.Member;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class AopController {
     @GetMapping
     public String home(){
          return "home";
     }
     
     @GetMapping("/get/{id}")
     public String get(@PathVariable Long id, @RequestParam String name) {
          //서비스 로직
          return id + " " + name;
     }
     
     @PostMapping ("/post")
     public Member post(@RequestBody Member member) {
          //서비스 로직
          return member;
     }
     
     @Timer
     @DeleteMapping("/delete")
     public void delete() {
          //삭제 서비스 로직: 소요시간 3초
          try {
               Thread.sleep(3000);
          } catch (InterruptedException e) {
               e.printStackTrace();
          }
     }
     
     @Decode
     @PutMapping("/put")
     public User put(@RequestBody User user) {
          //서비스 로직
          return user;
     }
}