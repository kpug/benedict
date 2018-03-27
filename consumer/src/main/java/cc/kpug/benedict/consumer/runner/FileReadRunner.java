package cc.kpug.benedict.consumer.runner;

import cc.kpug.benedict.consumer.services.GithubService;
import cc.kpug.benedict.consumer.utils.FileUtils;
import cc.kpug.benedict.consumer.utils.ParserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;


import java.nio.file.Paths;

/**
 * @author Lawrence
 * @note
 * @since 2018. 3. 11.
 * @version 0.0.1
 */
@Slf4j
@Component
public class FileReadRunner {

    final private UrlValidator urlValidator;
    final private GithubService githubService;

    public FileReadRunner(final UrlValidator urlValidator,
                          final GithubService githubService) {
        this.urlValidator = urlValidator;
        this.githubService = githubService;
    }

    public void run(final String filePath) throws Exception {
        if (filePath.equals("")) {
            log.info("Do you want to run Consumer without filepath, idiot?");
            return;
        } else {
            final Flux<String> lineFlux = FileUtils.fromPath(Paths.get(filePath));
            lineFlux.filter(urlValidator::isValid)
                    .map(url -> githubService.getFileRawData(url))
                    .map(ParserUtils::extractMethodSignature)
                    .map(ParserUtils::extractMethodName)
                    .doOnNext(names ->
                            log.info("{}", String.join("\n", names))
                    ).blockLast();
        }
    }
}
