package app.web.plcReader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public
class DosingDevicePlcData {
    private  int recordNumber;
    private float lastMeasure;
    private int amountBelowMeasures;
    private int amountCorrectMeasures;
    private int amountAboveMeasures;
    private float averageMeasure;
    private int correctMeasuresPercent;
    private int totalMaterial;
}
