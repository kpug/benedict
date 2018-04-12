package cc.kpug.benedict.consumer.services;

import cc.kpug.benedict.consumer.domain.MethodDescription;
import cc.kpug.benedict.consumer.repository.MethodDescriptionRepository;
import org.springframework.stereotype.Service;

/**
 * Created by jayden.uk on 2018. 4. 9...
 */
@Service
public class MethodDescriptionService {

    private final MethodDescriptionRepository repository;

    public MethodDescriptionService(MethodDescriptionRepository methodDescriptionRepository) {
        this.repository = methodDescriptionRepository;
    }

    public void insert(final MethodDescription methodDescription) {
        this.repository.save(methodDescription);
    }
}
