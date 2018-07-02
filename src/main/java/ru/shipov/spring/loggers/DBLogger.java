package ru.shipov.spring.loggers;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.shipov.spring.beans.Event;

public class DBLogger implements EventLogger {
    private final JdbcTemplate jdbcTemplate;

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void logEvent(Event event) {

    }
}
