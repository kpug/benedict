package cc.kpug.benedict.provider;

import static java.util.stream.Collectors.toList;

import cc.kpug.benedict.provider.service.JavaFileExtractor;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 * Created by before30 on 08/03/2018.
 */
@SpringBootApplication
@Slf4j
public class ProviderApplication implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        final File output = new File("__output.tmp");
        final PrintWriter writer = new PrintWriter(output);

        JsonNode node = restTemplate.getForObject("https://api.github.com/search/repositories?q=language:java&sort=stars&order=desc", JsonNode.class);
        JsonNode itemArray = node.get("items");
        ArrayList<GitRepositoryInfo> repositoryInfos = new ArrayList<>();
        for (int i=0; i<2; i++) {
            GitRepositoryInfo gitRepositoryInfo = new GitRepositoryInfo();
            gitRepositoryInfo.setDefaultBranch(itemArray.get(i).get("default_branch").asText());
            gitRepositoryInfo.setFullName(itemArray.get(i).get("full_name").asText());
            gitRepositoryInfo.setHtmlUrl(itemArray.get(i).get("html_url").asText());

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
