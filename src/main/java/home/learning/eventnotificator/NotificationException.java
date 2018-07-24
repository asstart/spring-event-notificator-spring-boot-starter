package home.learning.eventnotificator;

public class NotificationException extends RuntimeException {

    public NotificationException() {
        super();
    }

    public NotificationException(String s) {
        super(s);
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotificationException(Throwable cause) {
        super(cause);
    }

}
