package cc.kpug.benedict.provider.conf;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created by before30 on 08/03/2018.
 */

@Component
public class HealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check();

        if (errorCode != 0) {
            return Health.down()
                    .withDetail("Error Code", errorCode).build();
        }

        return Health.up().build();
    }

    private int check() {
        // need to check infra
        return 0;
    }
}
