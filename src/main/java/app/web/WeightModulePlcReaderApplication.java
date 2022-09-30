package app.web;

import app.web.weightModule.WeightModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class WeightModulePlcReaderApplication {
    private final Logger logger = LoggerFactory.getLogger(WeightModulePlcReaderApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WeightModulePlcReaderApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    void startUp() {
        WeightModule weightModule = new WeightModule("192.168.0.247");
        weightModule.processData();
    }

}
