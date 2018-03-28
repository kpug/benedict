package cc.kpug.benedict.provider;

import cc.kpug.benedict.provider.domain.GitRepositoryInfo;
import cc.kpug.benedict.provider.service.JavaFileExtractor;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by before30 on 08/03/2018.
 */
@SpringBootApplication
@Slf4j
public class ProviderApplication implements CommandLineRunner {


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public void run(String... args) throws Exception {
        final File output = new File("__output.tmp");
        final PrintWriter writer = new PrintWriter(output);

        JsonNode node = restTemplate.getForObject("https://api.github.com/search/repositories?q=language:java&sort=stars&order=desc", JsonNode.class);
        JsonNode itemArray = node.get("items");
        ArrayList<GitRepositoryInfo> repositoryInfos = new ArrayList<>();

        for (int i=0; i<2; i++) {
            GitRepositoryInfo gitRepositoryInfo = mapper.treeToValue(itemArray.get(i), GitRepositoryInfo.class);
            repositoryInfos.add(gitRepositoryInfo);
            log.info("{}", gitRepositoryInfo.getDownloadUrl());

            JavaFileExtractor.extract(gitRepositoryInfo)
                .forEach(writer::println);
        }

        writer.close();
        output.renameTo(new File("output"));

    }

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
