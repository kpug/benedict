package cc.kpug.benedict.consumer.configs;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lawrence
 * @note
 * @since 2018. 3. 11.
 */
@Configuration
public class ConsumerConfig {

    @Bean
    public UrlValidator urlValidator(){
        return UrlValidator.getInstance();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
