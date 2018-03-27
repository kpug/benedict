package cc.kpug.benedict.consumer.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Lawrence
 * @note
 * @since 2018. 3. 11.
 * @version 0.0.1
 */
@Service
public class GithubServiceImpl implements GithubService {

    final RestTemplate restTemplate;

    public GithubServiceImpl(final RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public String getFileRawData(final String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
