package home.learning.eventnotificator.notificator.rabbitmq.properties;

import lombok.Data;

@Data
public class RabbitMq {
    public String host;
    public String queue;
    public String exchange;
    public Boolean durable;
    public Boolean exclusive;
    public Boolean autoDelete;
}
