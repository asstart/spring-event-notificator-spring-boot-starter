package home.learning.eventnotificator.notificator.file;

import home.learning.eventnotificator.formatter.MessageFormatter;
import home.learning.eventnotificator.notificator.Notificator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfig {
    @Bean
    @Qualifier(value = "fileNotificator")
    public Notificator fileNotificator() {
        return new FileNotificator();
    }

    @Bean
    public FileWriter fileWriter() {
        return new FileWriter();
    }

    @Bean
    @Qualifier("fileMessageFormatter")
    public MessageFormatter fileMessageFormatter() {
        return new FileMessageFormatter();
    }
}
