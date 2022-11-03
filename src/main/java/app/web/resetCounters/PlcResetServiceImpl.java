package app.web.resetCounters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class PlcResetServiceImpl implements PlcResetService {
    private final PlcDataCollector plcDataCollector;


    @Override
    public void resetCounters(ResetCounterMessage msg) {
        msg.getLineIds()
                .stream()
                .forEach(this::resetCounter);
    }

    private void resetCounter(long lineId){

    }
}
