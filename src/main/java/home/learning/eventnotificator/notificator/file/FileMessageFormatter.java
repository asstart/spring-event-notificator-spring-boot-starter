package home.learning.eventnotificator.notificator.file;

import home.learning.eventnotificator.formatter.AbstractMessageFormatter;
import org.springframework.context.event.ApplicationContextEvent;

public class FileMessageFormatter extends AbstractMessageFormatter {

    public String format(ApplicationContextEvent event) {
        return super.format(event);
    }
}
