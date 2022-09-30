package app.web.weightModule;

import app.web.plcReader.PlcReaderFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeightModule {
    private final Logger logger = LoggerFactory.getLogger(WeightModule.class);
    private final PlcReader plcReader;

    public WeightModule(String plcAddress) {
        this.plcReader = new PlcReaderFacade(plcAddress);
        logger.info("Weight module with PLC address created: {}", plcAddress);
    }

    public void processData() {
        readDataFromPlc();
    }

    private void readDataFromPlc() {
        try {
            plcReader.createSession();

            plcReader.closeSession();
        } catch (Exception e) {
            logger.error("Error during fetching data from PLC Controller");
            logger.error(e.getLocalizedMessage());
        }

    }
}
