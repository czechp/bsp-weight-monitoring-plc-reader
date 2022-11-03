package app.web.resetCounters;

import java.util.List;

interface PlcDataCollector {
    List<PlcResetInfo> getPlcResetInfo();
}
