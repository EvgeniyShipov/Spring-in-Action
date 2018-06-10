package ru.shipov.spring;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private final int id;
    private final Date date;
    private final DateFormat dataFormat;
    private String message;

    public Event(Date date, DateFormat dataFormat) {
        this.id = (int) (Math.random() * 999_999);
        this.date = date;
        this.dataFormat = dataFormat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + dataFormat.format(date) +
                '}';
    }
}
