package ru.shipov.spring.loggers;

import org.springframework.stereotype.Component;
import ru.shipov.spring.beans.Event;

@Component
public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
