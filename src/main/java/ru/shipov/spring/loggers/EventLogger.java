package ru.shipov.spring.loggers;

import ru.shipov.spring.beans.Event;

public interface EventLogger {

    void logEvent(Event event);
}
