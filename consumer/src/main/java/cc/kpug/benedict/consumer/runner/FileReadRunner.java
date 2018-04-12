package cc.kpug.benedict.consumer.runner;

import cc.kpug.benedict.consumer.domain.MethodDescription;
import cc.kpug.benedict.consumer.services.GithubService;
import cc.kpug.benedict.consumer.services.MethodDescriptionService;
import cc.kpug.benedict.consumer.utils.FileUtils;
import cc.kpug.benedict.consumer.utils.ParserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;


import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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
    final private MethodDescriptionService methodDescriptionService;


    public FileReadRunner(final UrlValidator urlValidator,
                          final GithubService githubService,
                          final MethodDescriptionService methodDescriptionService) {
        this.urlValidator = urlValidator;
        this.githubService = githubService;
        this.methodDescriptionService = methodDescriptionService;
    }

    public void run(final String filePath) throws Exception {
        if (filePath.equals("")) {
            log.info("Do you want to run Consumer without filepath, idiot?");
            return;
        } else {
            final Flux<String> lineFlux = FileUtils.fromPath(Paths.get(filePath));
            //                        log.info(methodDescription.toString());
            lineFlux.filter(urlValidator::isValid)
                    .flatMap(u -> Flux.fromIterable(getMethodDescription(u)))
                    .doOnNext(methodDescriptionService::insert).blockLast();
        }
    }

    private List<MethodDescription> getMethodDescription(final String url) {

        final Tuple3<String, String, String> fileInfo = ParserUtils.extractFileInfo(url);
        final String fileRawData = githubService.getFileRawData(url);

        List<Tuple2<Integer, String>> methodAndLineNumbers = ParserUtils.extractMethodAndLineNumber(fileRawData);

        return methodAndLineNumbers.stream()
                .map(t -> Tuples.of(t.getT1(), ParserUtils.extractMethodName(t.getT2())))
                .flatMap(t ->
                        t.getT2().stream()
                        .map(s -> new MethodDescription(fileInfo.getT1(), fileInfo.getT2(), fileInfo.getT3(), t.getT1(), s))
                )
                .collect(Collectors.toList());
    }

}
