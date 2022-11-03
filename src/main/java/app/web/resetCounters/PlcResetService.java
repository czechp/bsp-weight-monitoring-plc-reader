package app.web.resetCounters;

interface PlcResetService {
    void resetCounters(ResetCounterMessage msg);
}
