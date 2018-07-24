package home.learning.eventnotificator.notificator.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import home.learning.eventnotificator.NotificationException;
import home.learning.eventnotificator.formatter.MessageFormatter;
import home.learning.eventnotificator.notificator.Notificator;
import home.learning.eventnotificator.notificator.NotificatorProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ApplicationContextEvent;

import java.io.IOException;

public class RabbitMqNotificator implements Notificator {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqNotificator.class);

    @Autowired
    private NotificatorProperties notificatorProperties;

    @Autowired
    @Qualifier("rabbitMqMessageFormatter")
    private MessageFormatter messageFormatter;

    private String queueName;

    @Override
    public void notify(ApplicationContextEvent applicationContextEvent) {
        if (notificatorProperties.rabbitmq != null) {
            Connection connection = getConnection();
            Channel channel = getChannel(connection);
            final String message = messageFormatter.format(applicationContextEvent);
            sendMessage(channel, message);
        }
    }

    private Connection getConnection() {
        try {
            if (notificatorProperties.rabbitmq.host != null) {
                ConnectionFactory connectionFactory = new ConnectionFactory();
                connectionFactory.setHost(notificatorProperties.rabbitmq.host);
                return connectionFactory.newConnection();
            }
        } catch (Exception ex) {
            String message = String.format("Something goes wrong during creating rabbit mq connection, look at message: $s", ex.getMessage());
            logger.error(message, ex);
            throw new NotificationException(message, ex);
        }
        throw new NotificationException("Host isn't define in properties");
    }

    private Channel getChannel(Connection connection) {
        try {
            Channel channel = connection.createChannel();
            channel.queueDeclare(resolveQueueName()
                    , notificatorProperties.rabbitmq.durable != null ? notificatorProperties.rabbitmq.durable : false
                    , notificatorProperties.rabbitmq.exclusive != null ? notificatorProperties.rabbitmq.exclusive : false
                    , notificatorProperties.rabbitmq.autoDelete != null ? notificatorProperties.rabbitmq.autoDelete : false
                    , null);
            return channel;
        } catch (IOException ex) {
            String message = String.format("Something goes wrong during creating rabbit mq channel, look at message: $s", ex.getMessage());
            logger.error(message);
            throw new NotificationException(message, ex);
        }
    }

    private String resolveQueueName() {
        if (queueName != null) {
            return queueName;
        } else if (notificatorProperties.rabbitmq.queue != null) {
            queueName = notificatorProperties.rabbitmq.queue;
            logger.debug("Queue name: " + queueName);
            return queueName;
        }
        return "";
    }

    private void sendMessage(Channel channel, String message) {
        if (notificatorProperties.rabbitmq.exchange != null) {
            channel = declareCustomExchange(channel, notificatorProperties.rabbitmq.exchange);
            publishMessage(channel, notificatorProperties.rabbitmq.exchange, message);
        }
        publishMessage(channel, "", message);
    }

    private Channel declareCustomExchange(Channel channel, String exchange) {
        try {
            channel.exchangeDeclare(exchange, "fanout");
            return channel;
        } catch (IOException ex) {
            String errMessage = String.format("Something goes wrong during declaring custom exchange, look at error message: %s", ex.getMessage());
            logger.error(errMessage);
            throw new NotificationException(errMessage, ex);
        }
    }

    private void publishMessage(Channel channel, String exchange, String message) {
        try {
            channel.basicPublish(
                    exchange
                    , queueName
                    , null
                    , message.getBytes());
        } catch (IOException ex) {
            String errMessage = String.format("Something goes wrong during sending message, look at error message: %s", ex.getMessage());
            logger.error(errMessage);
            throw new NotificationException(errMessage, ex);
        }
    }
}
