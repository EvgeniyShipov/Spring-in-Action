package ru.shipov.spring.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.shipov.spring.beans.LoggingAspect;

import javax.annotation.Resource;


@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Resource(name = "loggingAspect")
    private LoggingAspect loggingAspect;

    @Bean
    public LoggingAspect aspect() {
        return loggingAspect;
    }
}
