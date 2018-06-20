package ru.shipov.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@Scope("prototype")
public class Event {
    private final int id;
    private String message;

    @Autowired
    @Qualifier("newDate")
    private final Date date;

    @Autowired
    private final DateFormat dataFormat;

    public Event(Date date, DateFormat dataFormat) {
        this.id = (int) (Math.random() * 999_999);
        this.date = date;
        this.dataFormat = dataFormat;
    }

    public static boolean isDay() {
        int hour = LocalDateTime.now().getHour();
        return hour >= 8 && hour < 17;
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
