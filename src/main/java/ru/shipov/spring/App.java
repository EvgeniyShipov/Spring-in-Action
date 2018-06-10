package ru.shipov.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private final Client client;
    private final EventLogger logger;
    private static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    public App(Client client, EventLogger logger) {
        this.client = client;
        this.logger = logger;
    }

    public void logEvent(String message) {
        Event event = context.getBean("event", Event.class);
        event.setMessage(message.replace(client.getId(), client.getFullName()));
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        App app = context.getBean("app", App.class);

        app.logEvent("Hi 1!");

        context.close();
    }
}
