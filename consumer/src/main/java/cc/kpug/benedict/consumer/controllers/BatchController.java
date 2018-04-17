package cc.kpug.benedict.consumer.controllers;

import cc.kpug.benedict.consumer.runner.FileReadRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lawrence
 * @version 0.0.2
 * @note
 * @since 2018. 4. 10.
 */
@RestController
@RequestMapping(value = "/batch")
@Slf4j
public class BatchController {

    @Value(value = "${file.path}")
    private String filePath;

    private FileReadRunner fileReadRunner;

    public BatchController(FileReadRunner fileReadRunner) {
        this.fileReadRunner = fileReadRunner;
    }

    @GetMapping(value="/parse/output")
    public void parseOutput() throws Exception {
        log.info("Load file path: {}", filePath);
        fileReadRunner.run(filePath);
    }

    @GetMapping(value="/parse/synonyms")
    public void parseSynonyms(){

    }
}
