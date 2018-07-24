package home.learning.eventnotificator;

import home.learning.eventnotificator.events.ActivateListener;
import home.learning.eventnotificator.events.StopListener;
import home.learning.eventnotificator.formatter.EventNameResolver;
import home.learning.eventnotificator.notificator.NotificatorProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableConfigurationProperties(NotificatorProperties.class)
public class ApplicationConfig {

    @Bean
    public ActivateListener activateListener() {
        return new ActivateListener();
    }

    @Bean
    public StopListener stopListener() {
        return new StopListener();
    }

    @Bean
    public EventNameResolver eventNameResolver() {
        return new EventNameResolver();
    }
}
