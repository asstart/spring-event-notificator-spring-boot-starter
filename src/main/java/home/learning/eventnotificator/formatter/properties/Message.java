package home.learning.eventnotificator.formatter.properties;

import lombok.Data;

@Data
public class Message {
    /**
     * Available macros
     * [date]
     * [event]
     */
    public String template;
    public String dateFormat;
}
