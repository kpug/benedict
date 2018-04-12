package cc.kpug.benedict.consumer;

import cc.kpug.benedict.consumer.runner.FileReadRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by before30 on 08/03/2018.
 */
@SpringBootApplication
@Slf4j
public class ConsumerApplication implements CommandLineRunner {

    @Value(value = "${file.path}")
    private String filePath;

    @Autowired
    FileReadRunner fileReadRunner;

    @Override
    public void run(String... args) throws Exception {
        log.info("Load file path: {}", filePath);
        fileReadRunner.run(filePath);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}