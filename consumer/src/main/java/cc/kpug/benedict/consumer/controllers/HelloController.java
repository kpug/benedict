package cc.kpug.benedict.consumer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lawrence
 * @note
 * @since 2018. 3. 11.
 * @version 0.0.1
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "world";
    }

}
