package home.learning.eventnotificator.notificator.file;

import home.learning.eventnotificator.formatter.MessageFormatter;
import home.learning.eventnotificator.notificator.Notificator;
import home.learning.eventnotificator.notificator.NotificatorProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ApplicationContextEvent;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileNotificator implements Notificator {

    private static final Logger logger = LoggerFactory.getLogger(FileNotificator.class);

    @Autowired
    private NotificatorProperties notificatorProperties;

    @Autowired
    private FileWriter fileWriter;

    @Autowired
    @Qualifier("fileMessageFormatter")
    private MessageFormatter fileMessageFormatter;

    @Override
    public void notify(ApplicationContextEvent applicationContextEvent) {
        if (notificatorProperties.file != null && notificatorProperties.file.path != null) {
            final Path path = Paths.get(notificatorProperties.file.path);
            final String message = fileMessageFormatter.format(applicationContextEvent);
            fileWriter.write(path, message);
        }
    }
}
