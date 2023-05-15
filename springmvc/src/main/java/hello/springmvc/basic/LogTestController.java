package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@Slf4j
@RestController
public class LogTestController {
//     private final Logger log = LoggerFactory.getLogger(getClass());
     
     @RequestMapping("/log-test")
     public String logTest(){
          String name = "Spring";
          
          log.trace("trace log={}", name);
          log.debug("trace debug={}", name);
          log.info("trace info={}", name);
          log.warn("trace warn={}", name);
          log.error("info error={}", name);
          return "ok";
     }
}
