package cc.kpug.benedict.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by before30 on 08/03/2018.
 */

@SpringBootApplication
@RestController
@Slf4j
public class ConsumerApplication implements CommandLineRunner {

    @GetMapping("/hello")
    public String hello() {
        return "consumer";
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("hello world!");
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}