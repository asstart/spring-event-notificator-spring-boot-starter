package home.learning.eventnotificator.formatter;

import home.learning.eventnotificator.NotificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class EventNameResolver {

    private static final Logger logger = LoggerFactory.getLogger(EventNameResolver.class);

    public String resolveEventName(ApplicationContextEvent applicationContextEvent){
        if(applicationContextEvent instanceof ContextRefreshedEvent){
            return "ContextRefreshedEvent";
        }else if(applicationContextEvent instanceof ContextClosedEvent){
            return "ContextClosedEvent";
        }
        String errMessage = "Undefind ApplicationContextEvent: " + applicationContextEvent.toString();
        logger.error(errMessage);
        throw new NotificationException(errMessage);
    }
}
