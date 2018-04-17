package cc.kpug.benedict.consumer.controllers;

import cc.kpug.benedict.consumer.services.AnalogueTermService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lawrence
 * @version 0.0.2
 * @note
 * @since 2018. 4. 10.
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/dict")
public class DictionaryController {

    private AnalogueTermService analogueTermService;

    public DictionaryController(AnalogueTermService analogueTermService) {
        this.analogueTermService = analogueTermService;
    }

    @GetMapping
    public String getDict() {
        List<String> analogueTerm = analogueTermService.findAnalogueTerm("");
        return "";
    }
}
