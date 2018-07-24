package home.learning.eventnotificator.events;

import home.learning.eventnotificator.notificator.Notificator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ActivateListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    @Qualifier("fileNotificator")
    private Notificator fileNotificator;

    @Autowired
    @Qualifier("rabbitMqNotificator")
    private Notificator rabbitMqNotificator;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        fileNotificator.notify(event);
        rabbitMqNotificator.notify(event);
    }
}
