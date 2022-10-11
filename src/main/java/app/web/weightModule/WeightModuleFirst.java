package app.web.weightModule;

import app.web.configuration.BackendConfiguration;
import app.web.configuration.PlcConfiguration;
import app.web.plcReader.PlcReaderFacade;
import app.web.weightModule.valueObject.WeightModuleFirstData;

import java.io.IOException;

public class WeightModuleFirst extends WeightModuleAbstract<WeightModuleFirstData> {
    public WeightModuleFirst(PlcConfiguration plcConfiguration, BackendConfiguration backendConfiguration) {
        super(plcConfiguration, backendConfiguration);
    }

    @Override
    WeightModuleFirstData readDataFromPlc() throws IOException {
        PlcReader plcReader = new PlcReaderFacade(plcConfiguration);
        plcReader.createSession();
        final  var dataFromPLC = plcReader.readModuleBasicData();
        plcReader.closeSession();
        return dataFromPLC;
    }
}
