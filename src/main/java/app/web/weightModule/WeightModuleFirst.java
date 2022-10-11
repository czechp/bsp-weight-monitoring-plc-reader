package app.web.weightModule;

import app.web.configuration.RequestSenderConfiguration;
import app.web.configuration.PlcConfiguration;
import app.web.plcReader.PlcReaderFacade;
import app.web.weightModule.valueObject.WeightModuleFirstData;

import java.io.IOException;

public class WeightModuleFirst extends WeightModuleAbstract<WeightModuleFirstData> {
    public WeightModuleFirst(PlcConfiguration plcConfiguration, RequestSenderConfiguration requestSenderConfiguration) {
        super(plcConfiguration, requestSenderConfiguration);
    }

    @Override
    WeightModuleFirstData readDataFromPlc() throws IOException {
        PlcReader plcReader = new PlcReaderFacade(plcConfiguration);
        final  var dataFromPLC = plcReader.readFirstModuleData();
        return dataFromPLC;
    }
}
