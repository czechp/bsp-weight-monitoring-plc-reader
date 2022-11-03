package app.web.resetCounters;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class PlcResetInfo {
    private final String ipAddress;
    private final long lineId;
    private final int resetBitBlock;

    public PlcResetInfo(String ipAddress, long lineId) {
        this.ipAddress = ipAddress;
        this.lineId = lineId;
        this.resetBitBlock = 23;
    }
}
