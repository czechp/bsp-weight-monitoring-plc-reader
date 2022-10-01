package app.web.weightModule;

import java.io.IOException;
import java.net.URISyntaxException;

public interface RequestSender {
    void sendBasicModuleData(String basicModuleJson) throws URISyntaxException, IOException, InterruptedException;
}
