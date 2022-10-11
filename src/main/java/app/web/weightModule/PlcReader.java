package app.web.weightModule;

import app.web.weightModule.valueObject.WeightModuleFirstData;

import java.io.IOException;

public interface PlcReader {
    void createSession();
    void closeSession() throws IOException;

    WeightModuleFirstData readFirstModuleData();
}
