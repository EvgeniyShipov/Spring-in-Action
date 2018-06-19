package ru.shipov.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.shipov.spring.beans.Client;
import ru.shipov.spring.beans.Event;
import ru.shipov.spring.beans.EventType;
import ru.shipov.spring.configs.AppConfig;
import ru.shipov.spring.configs.LoggerConfig;
import ru.shipov.spring.loggers.EventLogger;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class App {
    @Autowired
    private Client client;

    @Resource(name = "defaultLogger")
    private EventLogger logger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;
    //    private static ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    private static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    public App() {
    }

    App(Client client, EventLogger logger, Map<EventType, EventLogger> loggers) {
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
        context.register(AppConfig.class, LoggerConfig.class);
        context.scan("ru.shipov.spring");
        context.refresh();

        App app = context.getBean("app", App.class);
        Client client = context.getBean(Client.class);
        Event event = context.getBean(Event.class);

        app.logEvent("Hi 1!", EventType.INFO);
        app.logEvent("1, wake up!", EventType.ERROR);

        context.close();
    }
}
