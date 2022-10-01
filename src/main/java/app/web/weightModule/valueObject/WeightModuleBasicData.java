package app.web.weightModule.valueObject;

import app.web.plcReader.PlcModuleBasicData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
public class WeightModuleBasicData {
    private float productUpRangeWeight = 0.0f;
    private float productDownRangeWeight = 0.0f;
    private int currentDosingDevice = 0;
    private float currentMeasure = 0.0f;
    private boolean status = false;
    private float totalMaterialWeight = 0.0f;
    private long totalProductPcs = 0L;
    private float correctProductPercent = 0.0f;

    public static WeightModuleBasicData create(PlcModuleBasicData plcData) {
        return new WeightModuleBasicData(
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
}
