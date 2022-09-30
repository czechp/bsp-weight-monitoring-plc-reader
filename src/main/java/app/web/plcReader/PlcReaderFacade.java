package app.web.plcReader;

import app.web.weightModule.PlcReader;
import com.github.s7connector.api.S7Connector;
import com.github.s7connector.api.S7Serializer;
import com.github.s7connector.api.factory.S7ConnectorFactory;
import com.github.s7connector.api.factory.S7SerializerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PlcReaderFacade implements PlcReader {
    private final Logger logger = LoggerFactory.getLogger(PlcReaderFacade.class);
    private final String plcAddress;
    private S7Connector s7Connector;
    private S7Serializer s7Serializer;

    public PlcReaderFacade(String plcAddress) {
        this.plcAddress = plcAddress;
    }

    @Override
    public void createSession() {
        s7Connector = S7ConnectorFactory
                .buildTCPConnector()
                .withHost(plcAddress)
                .withRack(0) //optional
                .withSlot(1) //optional
                .build();
        s7Serializer = S7SerializerFactory.buildSerializer(s7Connector);
        logger.info("Connection with PLC: {} established", plcAddress);
    }

    @Override
    public void closeSession() throws IOException {
        s7Connector.close();
        logger.info("Connection with PLC: {} closed", plcAddress);
    }
}
