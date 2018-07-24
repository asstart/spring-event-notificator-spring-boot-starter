package home.learning.eventnotificator.formatter;

import home.learning.eventnotificator.NotificationException;
import home.learning.eventnotificator.notificator.NotificatorProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationContextEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AbstractMessageFormatter implements MessageFormatter {

    private static final Logger logger = LoggerFactory.getLogger(AbstractMessageFormatter.class);

    @Autowired
    private NotificatorProperties notificatorProperties;

    @Autowired
    private EventNameResolver eventNameResolver;

    @Override
    public String format(ApplicationContextEvent event) {
        return buildMessage(event);
    }

    private String buildMessage(ApplicationContextEvent event) {
        if (notificatorProperties.message != null && notificatorProperties.message.template != null) {
            String templateMessage = notificatorProperties.message.template;
            templateMessage = getDate(templateMessage);
            templateMessage = getEvenet(templateMessage, event);
            logger.debug("Message: %s", templateMessage);
            return templateMessage;
        }
        throw new NotificationException("Empty message, please fill property [notification.message.format] in you application file");
    }

    private String getDate(String template) {
        if(notificatorProperties.message.dateFormat != null){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(notificatorProperties.message.dateFormat);
            return template.replace(MessageMacros.DATE.getMacrosName(), dateTimeFormatter.format(LocalDateTime.now()));
        }
        return template.replace(MessageMacros.DATE.getMacrosName(), LocalDateTime.now().toString());
    }

    private String getEvenet(String template, ApplicationContextEvent event){
        return template.replace(MessageMacros.EVENT.getMacrosName(), eventNameResolver.resolveEventName(event));
    }
}
