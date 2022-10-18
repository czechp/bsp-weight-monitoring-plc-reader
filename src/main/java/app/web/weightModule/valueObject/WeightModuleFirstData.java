package app.web.weightModule.valueObject;

import app.web.plcReader.DosingDevicePlcData;
import app.web.plcReader.PlcModuleFirstData;
import app.web.utils.JsonConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
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
    private List<DosingDevice> dosingDevices;
    public static WeightModuleFirstData create(PlcModuleFirstData plcData, List<DosingDevicePlcData> dosingDevicesPlcData) {
        return new WeightModuleFirstData(
                plcData.productUpRangeWeight,
                plcData.productDownRangeWeight,
                plcData.currentDosingDevice,
                plcData.currentMeasure,
                plcData.status,
                plcData.totalMaterialWeight,
                plcData.totalProductPcs,
                plcData.correctProductPercent,
                dosingDevicesPlcData.stream()
                        .map(DosingDevice::create)
                        .collect(Collectors.toList())
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
