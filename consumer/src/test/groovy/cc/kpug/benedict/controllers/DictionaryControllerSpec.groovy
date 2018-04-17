package cc.kpug.benedict.controllers;

import cc.kpug.benedict.consumer.ConsumerApplication
import cc.kpug.benedict.consumer.controllers.DictionaryController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 * @author Lawrence
 * @version 0.0.2
 * @note
 * @since 2018. 4. 10.
 */
@SpringBootTest(classes = ConsumerApplication)
class DictionaryControllerSpec extends Specification {

    @Autowired
    DictionaryController controller

    def "controller is not null"() {
        expect:
        controller != null
        controller.getDict() == ""
    }
}
