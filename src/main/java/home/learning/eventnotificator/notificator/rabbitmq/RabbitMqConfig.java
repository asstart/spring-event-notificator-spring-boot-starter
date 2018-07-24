package home.learning.eventnotificator.notificator.rabbitmq;

import home.learning.eventnotificator.formatter.MessageFormatter;
import home.learning.eventnotificator.notificator.Notificator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    @Qualifier(value = "rabbitMqNotificator")
    public Notificator rabbitMqNotificator() {
        return new RabbitMqNotificator();
    }

    @Bean
    @Qualifier(value = "rabbitMqMessageFormatter")
    public MessageFormatter rabbitMqMessageFormatter() {
        return new RabbitMqMessageFormatter();
    }
}
