package home.learning.eventnotificator.events;

import home.learning.eventnotificator.notificator.Notificator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class StopListener implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    @Qualifier("fileNotificator")
    private Notificator fileNotificator;

    @Autowired
    @Qualifier("rabbitMqNotificator")
    private Notificator rabbitMqNotificator;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        fileNotificator.notify(event);
        rabbitMqNotificator.notify(event);
    }
}
