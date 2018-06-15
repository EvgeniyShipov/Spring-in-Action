package ru.shipov.spring;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    protected String fileName;
    protected File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

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
