package app.web;

import app.web.WeightModulePlcReaderApplication;
import app.web.configuration.PlcConfiguration;
import app.web.configuration.RequestSenderConfiguration;
import app.web.weightModule.WeightModuleFirst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Profile({"production"})
class StartupProduction {
    private final Logger logger = LoggerFactory.getLogger(WeightModulePlcReaderApplication.class);

    @Value("${backend.url}")
    private String BACKEND_URL;

    @Value("${backend.login}")
    private String BACKEND_LOGIN;

    @Value("${backend.password}")
    private String BACKEND_PASSWORD;


    @Scheduled(fixedDelay = 60_000)
    void startUp() throws IOException {
        logger.info("Application started in production mode");

    }
}
