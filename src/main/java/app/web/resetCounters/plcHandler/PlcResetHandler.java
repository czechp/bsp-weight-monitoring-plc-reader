package app.web.resetCounters.plcHandler;

import app.web.resetCounters.plcData.PlcResetInfo;

public interface PlcResetHandler {
    void resetCounter(PlcResetInfo plcResetInfo);
}
