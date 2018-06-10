package ru.shipov.spring;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int casheSize) {
        super(fileName);
        this.cacheSize = casheSize;
        cache = new ArrayList<>();
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize)
            writeEventsFromCahe();
        cache.clear();
    }

    private void writeEventsFromCahe() {
        cache.forEach(event -> {
            try {
                FileUtils.writeStringToFile(file, event.toString(), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void destroy() {
        if (!cache.isEmpty())
            writeEventsFromCahe();
    }
}
