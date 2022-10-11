package app.web.weightModule;

import app.web.configuration.PlcConfiguration;
import app.web.configuration.RequestSenderConfiguration;
import app.web.plcReader.PlcReaderFacade;
import app.web.weightModule.valueObject.WeightModuleLastData;

import java.io.IOException;

public class WeightModuleLast extends WeightModuleAbstract<WeightModuleLastData> {

    public WeightModuleLast(PlcConfiguration plcConfiguration, RequestSenderConfiguration requestSenderConfiguration) {
        super(plcConfiguration, requestSenderConfiguration);
    }

    @Override
    WeightModuleLastData readDataFromPlc() throws IOException {
        final var plcReader = new PlcReaderFacade(plcConfiguration);
        return plcReader.readLastModuleData();
    }
}
