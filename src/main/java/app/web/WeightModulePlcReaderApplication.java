package app.web;

import app.web.configuration.RequestSenderConfiguration;
import app.web.configuration.PlcConfiguration;
import app.web.plcReader.PlcReaderFacade;
import app.web.weightModule.WeightModuleFirst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

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

    @Scheduled(fixedDelay = 60_000)
    void startUp() throws IOException {
        final var backendConfiguration = new RequestSenderConfiguration(1L, BACKEND_URL, "/api/weight-modules/data/",  BACKEND_LOGIN, BACKEND_PASSWORD);
        final var plcConfigurationModuleFirst = new PlcConfiguration("192.168.0.247", 32);
        final var plcConfigurationModuleLast = new PlcConfiguration("192.168.0.247", 33);

        WeightModuleFirst weightModuleFirst = new WeightModuleFirst(plcConfigurationModuleFirst, backendConfiguration);
        weightModuleFirst.processData();
    }

}
