package app.web.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlcConfiguration {
    private final String plcAddress;
    private final int dbNrBasicInfo;
}
