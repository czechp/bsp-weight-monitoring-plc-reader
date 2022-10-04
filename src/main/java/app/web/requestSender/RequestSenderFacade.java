package app.web.requestSender;

import app.web.configuration.BackendConfiguration;
import app.web.weightModule.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

public class RequestSenderFacade implements RequestSender {
    private final Logger logger = LoggerFactory.getLogger(RequestSenderFacade.class);
    private final String backendUrl;
    private final String login;
    private final String password;
    private final long moduleId;

    public RequestSenderFacade(BackendConfiguration backendConfiguration) {
        this.backendUrl = backendConfiguration.getBackendUrl();
        this.login = backendConfiguration.getLogin();
        this.password = backendConfiguration.getPassword();
        this.moduleId = backendConfiguration.getModuleId();
    }

    private static HttpRequest.BodyPublisher createRequestBody(String basicModuleJson) {
        return HttpRequest.BodyPublishers.ofString(basicModuleJson);
    }

    @Override
    public void sendBasicModuleData(String basicModuleJson) throws URISyntaxException, IOException, InterruptedException {
        final var ENDPOINT = "/api/weight-modules/data/" + moduleId;
        HttpRequest.Builder requestBuilder = createRequestBuilder(ENDPOINT);
        addJsonContentHeader(requestBuilder);
        final var httpRequest = requestBuilder.method("PATCH", createRequestBody(basicModuleJson)).build();

        sendRequest(httpRequest);
    }

    private HttpRequest.Builder createRequestBuilder(String ENDPOINT) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(backendUrl + ENDPOINT))
                .header("Authorization", hashAuthorizationHeader())
                .timeout(Duration.of(10, ChronoUnit.SECONDS));
    }

    private String hashAuthorizationHeader() {
        final var hashCode = Base64.getEncoder().encodeToString((login + ":" + password).getBytes());
        final var basicAuthorizationHeader = "Basic " + hashCode;
        return basicAuthorizationHeader;
    }

    private void addJsonContentHeader(HttpRequest.Builder builder) {
        builder.header("Content-Type", "application/json");
    }

    private void sendRequest(HttpRequest httpRequest) {
        final var httpClient = HttpClient.newBuilder().build();
        CompletableFuture<HttpResponse<String>> futureHttpResponse = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
        futureHttpResponse.thenAccept((httpResponse) -> {
            logger.info("Response from server with status: {}", httpResponse.statusCode());
        });
    }
}