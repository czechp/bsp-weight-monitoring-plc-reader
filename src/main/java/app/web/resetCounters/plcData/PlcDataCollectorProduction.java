package app.web.resetCounters.plcData;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile({"production"})
class PlcDataCollectorProduction implements PlcDataCollector{

    @Override
    public List<PlcResetInfo> getPlcResetInfo() {
        return List.of();
    }
}
