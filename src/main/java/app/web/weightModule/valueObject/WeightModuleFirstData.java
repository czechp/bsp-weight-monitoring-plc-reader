package app.web.weightModule.valueObject;

import app.web.plcReader.PlcModuleFirstData;
import app.web.utils.JsonConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class WeightModuleFirstData {
    private float productUpRangeWeight = 0.0f;
    private float productDownRangeWeight = 0.0f;
    private int currentDosingDevice = 0;
    private float currentMeasure = 0.0f;
    private boolean status = false;
    private float totalMaterialWeight = 0.0f;
    private long totalProductPcs = 0L;
    private float correctProductPercent = 0.0f;

    public static WeightModuleFirstData create(PlcModuleFirstData plcData) {
        return new WeightModuleFirstData(
                plcData.productUpRangeWeight,
                plcData.productDownRangeWeight,
                plcData.currentDosingDevice,
                plcData.currentMeasure,
                plcData.status,
                plcData.totalMaterialWeight,
                plcData.totalProductPcs,
                plcData.correctProductPercent
        );
    }

    @Override
    public String toString() {
        try {
            return JsonConverter.toJson(this);
        } catch (IOException e) {
            return "Cannot parse " + this.getClass().toString() + " to JSON";
        }
    }
}
