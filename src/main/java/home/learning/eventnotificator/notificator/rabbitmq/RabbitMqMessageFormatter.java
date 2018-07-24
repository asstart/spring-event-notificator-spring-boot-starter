package home.learning.eventnotificator.notificator.rabbitmq;

import home.learning.eventnotificator.formatter.AbstractMessageFormatter;
import org.springframework.context.event.ApplicationContextEvent;

public class RabbitMqMessageFormatter extends AbstractMessageFormatter {
    public String format(ApplicationContextEvent event) {
        return super.format(event);
    }
}
