package ru.shipov.spring.loggers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.shipov.spring.beans.Event;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class FileEventLogger implements EventLogger {

    @Value("src/main/resources/temp.txt")
    protected String fileName;
    protected File file;

    public FileEventLogger() {
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void init() {
        try {
            file = new File(fileName);
            if (!file.exists())
                file.createNewFile();
            file.canWrite();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", "UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
