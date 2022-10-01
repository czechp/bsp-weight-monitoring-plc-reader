package app.web.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BackendConfiguration {
    private String backendUrl;
    private String login;
    private String password;
}
