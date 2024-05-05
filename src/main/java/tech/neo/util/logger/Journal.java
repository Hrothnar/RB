package tech.neo.util.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Journal {
    public static final Logger LOGGER = Logger.getLogger(Journal.class.getSimpleName());

    public static void setupLogger() {
        try {
            FileHandler fileHandler = new FileHandler("./src/main/java/tech/neo/util/journal.log", true);
            LOGGER.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
