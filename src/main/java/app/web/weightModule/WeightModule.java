package app.web.weightModule;

import app.web.plcReader.PlcReaderFacade;
import app.web.weightModule.valueObject.WeightModuleBasicData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeightModule {
    private final Logger logger = LoggerFactory.getLogger(WeightModule.class);
    private final PlcReader plcReader;
    private PlcInfo plcInfo;
    private WeightModuleBasicData weightModuleBasicData;


    public WeightModule(String plcAddress, int dbNrBasicInfo) {
        this.plcInfo = new PlcInfo(plcAddress, dbNrBasicInfo);
        this.plcReader = new PlcReaderFacade(this.plcInfo.getPlcAddress());
        this.weightModuleBasicData = new WeightModuleBasicData();

        logger.info("Weight module with PLC address created: {}", plcAddress);
    }

    public void processData() {
        readDataFromPlc();
    }

    private void readDataFromPlc() {
        try {
            plcReader.createSession();
            plcReader.readModuleBasicData(plcInfo.getDbNrBasicInfo());
            plcReader.closeSession();
        } catch (Exception e) {
            logger.error("Error during fetching data from PLC Controller");
            logger.error(e.getLocalizedMessage());
        }

    }

    @AllArgsConstructor
    @Getter
    class PlcInfo {
        private final String plcAddress;
        private final int dbNrBasicInfo;
    }
}
