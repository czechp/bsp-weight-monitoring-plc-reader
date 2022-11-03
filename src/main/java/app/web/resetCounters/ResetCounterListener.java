package app.web.resetCounters;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
class ResetCounterListener {
    private final PlcResetService plcResetService;

    ResetCounterListener(PlcResetService plcResetService) {
        this.plcResetService = plcResetService;
    }

    @RabbitListener(queues = {"${rabbit.reset.queue}"})
    public void getResetMsg(ResetCounterMessage msg){
        System.out.println("Works");
        System.out.println(msg);
    }
}
