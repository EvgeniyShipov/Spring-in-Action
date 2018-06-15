package ru.shipov.spring;

public class Client {
    private String id;
    private String fullName;
    private String greeting;

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
