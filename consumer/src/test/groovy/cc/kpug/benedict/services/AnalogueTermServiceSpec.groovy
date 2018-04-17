package cc.kpug.benedict.service

import cc.kpug.benedict.consumer.ConsumerApplication
import cc.kpug.benedict.consumer.services.AnalogueTermService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
 *
 * @author Lawrence
 * @since 2018. 4. 10.
 * @note
 * @version 0.0.2
 */
@SpringBootTest(classes = ConsumerApplication)
class AnalogueTermServiceSpec extends Specification {

    @Autowired
    AnalogueTermService service

    def "service.findAnalogueTerm return List"() {
        expect:
        service != null
        service.findAnalogueTerm("").size() == 0
        service.findAnalogueTerm("")
    }
}