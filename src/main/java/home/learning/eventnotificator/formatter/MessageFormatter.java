package home.learning.eventnotificator.formatter;

import org.springframework.context.event.ApplicationContextEvent;

public interface MessageFormatter {

    String format(ApplicationContextEvent event);
}
