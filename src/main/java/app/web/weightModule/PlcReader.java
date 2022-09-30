package app.web.weightModule;

import java.io.IOException;

public interface PlcReader {
    void createSession();
    void closeSession() throws IOException;
}
