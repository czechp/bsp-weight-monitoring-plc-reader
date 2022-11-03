package app.web.resetCounters;

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
