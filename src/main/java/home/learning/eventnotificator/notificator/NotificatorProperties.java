package home.learning.eventnotificator.notificator;

import home.learning.eventnotificator.formatter.properties.Message;
import home.learning.eventnotificator.notificator.file.properties.File;
import home.learning.eventnotificator.notificator.rabbitmq.properties.RabbitMq;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("notification")
public class NotificatorProperties {

    public File file;
    public RabbitMq rabbitmq;
    public Message message;

}
