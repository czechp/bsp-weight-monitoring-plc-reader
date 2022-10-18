package app.web.weightModule.valueObject;

import app.web.plcReader.PlcDosingDeviceData;
import app.web.plcReader.PlcModuleLastData;
import app.web.utils.JsonConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class WeightModuleLastData {
    private WeightModuleFirstData moduleInfo;
    private LastModuleInfo lastModuleInfo;

    public static  WeightModuleLastData create(PlcModuleLastData module, List<PlcDosingDeviceData> plcDosingDevices) {
        WeightModuleFirstData moduleInfo = new WeightModuleFirstData(
                module.getProductUpRangeWeight(),
                module.getProductDownRangeWeight(),
                module.getCurrentDosingDevice(),
                module.getCurrentMeasure(),
                module.isStatus(),
                module.getTotalMaterialWeight(),
                module.getTotalProductPcs(),
                module.getCorrectProductPercent(),
                plcDosingDevices.stream().map(DosingDevice::create).collect(Collectors.toList())
        );

        LastModuleInfo lastModuleInfo = new LastModuleInfo(
                module.getIncorrectProductPcs(),
                module.getWeightDifference(),
                module.getCorrectToOverDosePercent(),
                module.getNotRefilledProductPcs(),
                module.getOverFilledProductPcs(),
                module.getOverFilledToNotRefilledPercent()
        );

        return new WeightModuleLastData(moduleInfo, lastModuleInfo);
    }

    @Override
    public String toString() {
        try {
            return JsonConverter.toJson(this);
        } catch (IOException e) {
            return "Cannot parse " + this.getClass().toString() + " to JSON";
        }
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    static
    class LastModuleInfo {
        private long incorrectProductPcs;
        private float weightDifference;
        private float correctToOverdosePercent;
        private long notRefilledProductPcs;
        private long overFilledProductPcs;
        private float overFilledToNotRefilledPercent;

        @Override
        public String toString() {
            try {
                return JsonConverter.toJson(this);
            } catch (IOException e) {
                return "Cannot parse " + this.getClass().toString() + " to JSON";
            }
        }
    }
}
