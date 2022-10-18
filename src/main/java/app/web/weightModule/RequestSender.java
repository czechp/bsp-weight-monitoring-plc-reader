package app.web.weightModule;

import java.io.IOException;
import java.net.URISyntaxException;

public interface RequestSender {
    void sendData(String requestBody) throws URISyntaxException, IOException, InterruptedException;
}
