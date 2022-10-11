package app.web;

import app.web.configuration.BackendConfiguration;
import app.web.configuration.PlcConfiguration;
import app.web.weightModule.WeightModuleFirst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication()
@EnableScheduling
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

    @Scheduled(fixedDelay = 30_000)
    void startUp() {
        final var backendConfiguration = new BackendConfiguration(1L, BACKEND_URL, BACKEND_LOGIN, BACKEND_PASSWORD);
        PlcConfiguration plcConfiguration = new PlcConfiguration("192.168.0.247", 32);

        WeightModuleFirst weightModuleFirst = new WeightModuleFirst(plcConfiguration, backendConfiguration);
        weightModuleFirst.processData();
    }

}
