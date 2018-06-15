package ru.shipov.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private final Client client;
    private final EventLogger logger;
    private final Map<EventType, EventLogger> loggers;
    private static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    public App(Client client, EventLogger logger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.logger = logger;
        this.loggers = loggers;
    }

    public void logEvent(String message, EventType eventType) {
        EventLogger logger = loggers.get(eventType);
        if (logger == null)
            logger = this.logger;
        Event event = context.getBean("event", Event.class);
        event.setMessage(message.replace(client.getId(), client.getFullName()));
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        App app = context.getBean("app", App.class);

        app.logEvent("Hi 1!", EventType.INFO);
        app.logEvent("1, wake up!", EventType.ERROR);

        context.close();
    }
}
