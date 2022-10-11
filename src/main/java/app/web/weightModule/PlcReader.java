package app.web.weightModule;

import app.web.weightModule.valueObject.WeightModuleFirstData;
import app.web.weightModule.valueObject.WeightModuleLastData;

import java.io.IOException;

public interface PlcReader {
    void createSession();
    void closeSession() throws IOException;

    WeightModuleFirstData readFirstModuleData();
    WeightModuleLastData readLastModuleData();
}
