package ru.shipov.spring.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.shipov.spring.beans.Client;
import ru.shipov.spring.loggers.EventLogger;

import java.text.DateFormat;
import java.util.Date;

@Configuration
@Import(LoggerConfig.class)
public class AppConfig {

    @Bean
    public Client client() {
        return new Client("1", "Bender");
    }

    @Autowired
    private EventLogger combinedEventLogger;

    @Bean
    public Date newDate() {
        return new Date();
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }
}
