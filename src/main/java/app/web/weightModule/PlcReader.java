package app.web.weightModule;

import app.web.weightModule.valueObject.WeightModuleBasicData;

import java.io.IOException;

public interface PlcReader {
    void createSession();
    void closeSession() throws IOException;

    WeightModuleBasicData readModuleBasicData(int dbNumber);
}
