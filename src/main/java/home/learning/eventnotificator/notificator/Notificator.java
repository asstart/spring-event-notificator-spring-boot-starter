package home.learning.eventnotificator.notificator;

import org.springframework.context.event.ApplicationContextEvent;

public interface Notificator {

    void notify(ApplicationContextEvent applicationContextEvent);
}
