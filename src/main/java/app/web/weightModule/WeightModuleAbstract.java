package app.web.weightModule;

import app.web.configuration.BackendConfiguration;
import app.web.configuration.PlcConfiguration;
import app.web.requestSender.RequestSenderFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

abstract class WeightModuleAbstract <T> {
    private Logger logger = LoggerFactory.getLogger(WeightModuleAbstract.class);

    protected T moduleData;
    protected PlcConfiguration plcConfiguration;
    private BackendConfiguration backendConfiguration;

    public WeightModuleAbstract(PlcConfiguration plcConfiguration, BackendConfiguration backendConfiguration) {
        this.plcConfiguration = plcConfiguration;
        this.backendConfiguration = backendConfiguration;
    }

    abstract  T readDataFromPlc() throws IOException;

    public void processData(){
        try{
            this.moduleData = readDataFromPlc();
            sendDataToBackend();
        }catch (Exception e){
            logger.error("Error during processing data");
            logger.error("PLC: {}", plcConfiguration.getPlcAddress());
        }
    }

    private void sendDataToBackend() throws URISyntaxException, IOException, InterruptedException {
        RequestSender requestSender = new RequestSenderFacade(backendConfiguration);
        requestSender.sendBasicModuleData(moduleData.toString());
    }
}
