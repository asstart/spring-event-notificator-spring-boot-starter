package home.learning.eventnotificator.notificator.file;

import home.learning.eventnotificator.NotificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class FileWriter {

    private static final Logger logger = LoggerFactory.getLogger(FileWriter.class);

    public void write(Path path, String text) {
        try {
            path = path.normalize();
            if (Files.isWritable(path)) {
                appendWrite(path, text);
            } else if (!Files.exists(path)) {
                writeNew(path, text);
            }
        } catch (IOException e) {
            String errMessage = "File writing error, look at message: " + e.getMessage();
            logger.error(errMessage, e);
            throw new NotificationException(errMessage, e);
        }
    }

    private void writeNew(Path path, String text) throws IOException {
        String[] pathElements = path.toString().split(path.getFileSystem().getSeparator());
        if (pathElements.length > 1) {
            String[] dirElements = Arrays.copyOfRange(pathElements, 1, pathElements.length - 1);
            Path dirs = Paths.get(pathElements[0], dirElements);
            Files.createDirectories(dirs);
        }
        Files.write(path, text.getBytes(), StandardOpenOption.CREATE);
    }

    private void appendWrite(Path path, String text) throws IOException {
        Files.write(path, text.getBytes(), StandardOpenOption.APPEND);
    }
}
