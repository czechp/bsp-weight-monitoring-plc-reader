package app.web.weightModule;

import app.web.configuration.BackendConfiguration;
import app.web.configuration.PlcConfiguration;
import app.web.plcReader.PlcReaderFacade;
import app.web.requestSender.RequestSenderFacade;
import app.web.weightModule.valueObject.WeightModuleBasicData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;

public class WeightModule {
    private final Logger logger = LoggerFactory.getLogger(WeightModule.class);
    private final PlcReader plcReader;
    private final PlcConfiguration plcConfiguration;
    private final RequestSender requestSender;
    private final BackendConfiguration backendConfiguration;
    private WeightModuleBasicData weightModuleBasicData;


    public WeightModule(PlcConfiguration plcConfiguration, BackendConfiguration backendConfiguration) {
        this.plcConfiguration = plcConfiguration;
        this.backendConfiguration = backendConfiguration;
        this.plcReader = new PlcReaderFacade(this.plcConfiguration.getPlcAddress());
        this.requestSender = new RequestSenderFacade(backendConfiguration);
        this.weightModuleBasicData = new WeightModuleBasicData();

        logger.info("Weight module with PLC address created: {}", plcConfiguration.getPlcAddress());
    }

    public void processData() {
        readDataFromPlc();
        sendDataToBackend();
    }

    private void readDataFromPlc() {
        try {
            plcReader.createSession();
            weightModuleBasicData = plcReader.readModuleBasicData(plcConfiguration.getDbNrBasicInfo());
            logger.info("Data read from PLC");
            plcReader.closeSession();
        } catch (Exception e) {
            logger.error("Error during fetching data from PLC Controller");
            logger.error(e.getLocalizedMessage());
        }
    }

    private void sendDataToBackend() {
        try {
            requestSender.sendBasicModuleData(weightModuleBasicData.toString());
        } catch (Exception e) {
            logger.error("Error during sending data to backend");
            logger.error(e.getLocalizedMessage());
        }
    }


}
