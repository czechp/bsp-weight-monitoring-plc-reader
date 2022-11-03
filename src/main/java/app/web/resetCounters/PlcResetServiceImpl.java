package app.web.resetCounters;

import app.web.resetCounters.listener.ResetCounterMessage;
import app.web.resetCounters.plcData.PlcDataCollector;
import app.web.resetCounters.plcHandler.PlcResetHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class PlcResetServiceImpl implements PlcResetService {
    private final PlcDataCollector plcDataCollector;
    private final PlcResetHandler plcResetHandler;

    @Override
    public void resetCounters(ResetCounterMessage msg) {
        msg.getLineIds()
                .stream()
                .forEach(this::resetCounter);
    }

    private void resetCounter(long lineId){
        plcDataCollector.getPlcResetInfo()
                .stream()
                .filter(plcResetInfo -> plcResetInfo.getLineId() == lineId)
                .findAny()
                .ifPresent(plcResetHandler::resetCounter);
    }
}
