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

    public void init(){
        this.file = new File(fileName);
        file.canWrite();
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
