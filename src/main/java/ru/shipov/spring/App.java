package ru.shipov.spring;

public class App {
    private final Client client;
    private final EventLogger logger;

    public App(Client client, EventLogger logger) {
        this.client = client;
        this.logger = logger;
    }

    public void logEvent(String message)  {
        logger.logEvent(message.replace(client.getId(), client.getFullName()));
    }

    public static void main(String[] args) {
        App app = new App(new Client("1", "Bender"), new ConsoleEventLogger());

        app.logEvent("Hi 1!");
    }
}
