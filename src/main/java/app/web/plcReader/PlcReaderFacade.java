package app.web.plcReader;

import app.web.configuration.PlcConfiguration;
import app.web.weightModule.PlcReader;
import app.web.weightModule.valueObject.WeightModuleFirstData;
import app.web.weightModule.valueObject.WeightModuleLastData;
import com.github.s7connector.api.S7Connector;
import com.github.s7connector.api.S7Serializer;
import com.github.s7connector.api.factory.S7ConnectorFactory;
import com.github.s7connector.api.factory.S7SerializerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PlcReaderFacade implements PlcReader {
    private final Logger logger = LoggerFactory.getLogger(PlcReaderFacade.class);
    private final PlcConfiguration plcConfiguration;
    private S7Connector s7Connector;
    private S7Serializer s7Serializer;

    public PlcReaderFacade(PlcConfiguration plcConfiguration) {
        this.plcConfiguration = plcConfiguration;
    }

    @Override
    public void createSession() {
        s7Connector = S7ConnectorFactory
                .buildTCPConnector()
                .withHost(plcConfiguration.getPlcAddress())
                .withRack(0) //optional
                .withSlot(1) //optional
                .build();
        s7Serializer = S7SerializerFactory.buildSerializer(s7Connector);
        logger.info("Connection with PLC: {} established", plcConfiguration.getPlcAddress());
    }

    @Override
    public void closeSession() throws IOException {
        s7Connector.close();
        logger.info("Connection with PLC: {} closed", plcConfiguration.getPlcAddress());
    }

    @Override
    public WeightModuleFirstData readFirstModuleData() {
        PlcModuleFirstData plcModuleFirstData = s7Serializer.dispense(PlcModuleFirstData.class, plcConfiguration.getDbNrBasicInfo(), 0);
        return WeightModuleFirstData.create(plcModuleFirstData);
    }

    @Override
    public WeightModuleLastData readLastModuleData() {
        PlcModuleLastData plcModuleLastData = s7Serializer.dispense(PlcModuleLastData.class, plcConfiguration.getDbNrBasicInfo(), 0);

        return WeightModuleLastData.create(plcModuleLastData);
    }
}
