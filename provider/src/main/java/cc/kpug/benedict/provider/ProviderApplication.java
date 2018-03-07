package cc.kpug.benedict.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by before30 on 08/03/2018.
 */
@SpringBootApplication
@Slf4j
public class ProviderApplication implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        log.info("hello world!");
    }

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
