package app.web;

import app.web.configuration.PlcConfiguration;
import app.web.configuration.RequestSenderConfiguration;
import app.web.weightModule.WeightModuleFirst;
import app.web.weightModule.WeightModuleLast;
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
        createFirstModules()
                .forEach(WeightModuleFirst::processData);
        createLastModules()
                .forEach(WeightModuleLast::processData);
    }

    private List<WeightModuleFirst> createFirstModules() {

        return List.of(
                new WeightModuleFirst(
                        new PlcConfiguration("192.168.1.46", 32, 36, 34),
                        new RequestSenderConfiguration(1, BACKEND_URL, "/api/weight-modules/data/", BACKEND_LOGIN, BACKEND_PASSWORD))
        );
    }

    private List<WeightModuleLast> createLastModules() {
        return List.of(
                new WeightModuleLast(
                        new PlcConfiguration("192.168.1.46", 33, 36, 35),
                        new RequestSenderConfiguration(1, BACKEND_URL, "/api/weight-modules-last/data/", BACKEND_LOGIN, BACKEND_PASSWORD)
                )
        );
    }


}
