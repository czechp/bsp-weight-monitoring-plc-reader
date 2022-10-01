package app.web;

import app.web.configuration.BackendConfiguration;
import app.web.configuration.PlcConfiguration;
import app.web.weightModule.WeightModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication()
public class WeightModulePlcReaderApplication {
    private final Logger logger = LoggerFactory.getLogger(WeightModulePlcReaderApplication.class);

    @Value("${backend.url}")
    private String BACKEND_URL;

    @Value("${backend.login}")
    private String BACKEND_LOGIN;

    @Value("${backend.password}")
    private String BACKEND_PASSWORD;

    public static void main(String[] args) {
        SpringApplication.run(WeightModulePlcReaderApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    void startUp() {
        final var backendConfiguration = new BackendConfiguration(BACKEND_URL, BACKEND_LOGIN, BACKEND_PASSWORD);
        final var weightModule = new WeightModule(new PlcConfiguration("192.168.0.247",32), backendConfiguration);
        weightModule.processData();
    }

}
