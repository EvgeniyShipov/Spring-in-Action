package ru.shipov.spring;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private Set<Event> cache;

    public CacheFileEventLogger(String fileName, int casheSize) {
        super(fileName);
        this.cacheSize = casheSize;
        cache = new HashSet<>(casheSize);
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache() {
        cache.forEach(event -> {
            try {
                FileUtils.writeStringToFile(file, event.toString() + "\n", "UTF-8", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void destroy() {
        if (!cache.isEmpty())
            writeEventsFromCache();
    }
}
