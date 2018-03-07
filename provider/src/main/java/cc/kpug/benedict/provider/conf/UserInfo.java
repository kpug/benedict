package cc.kpug.benedict.provider.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * Created by before30 on 08/03/2018.
 */
@Component
public class UserInfo implements InfoContributor {

    @Value("${spring.application.name:empty}")
    private String appName;

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("name", appName);
    }
}
