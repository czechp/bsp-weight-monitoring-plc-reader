package app.web.weightModule.valueObject;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class WeightModuleBasicData {
    private float productUpRangeWeight = 0.0f;
    private float productDownRangeWeight = 0.0f;
    private int currentDosingDevice = 0;
    private float currentMeasure = 0.0f;
    private boolean status = false;
    private float totalMaterialWeight = 0.0f;
    private long totalProductPcs = 0L;
    private float correctProductPercent = 0.0f;
}
