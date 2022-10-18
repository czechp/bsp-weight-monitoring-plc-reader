package app.web.weightModule.valueObject;

import app.web.plcReader.DosingDevicePlcData;
import app.web.utils.JsonConverter;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.IOException;

@AllArgsConstructor
public class DosingDevice {
    private  int recordNumber;
    private float lastMeasure;
    private int amountBelowMeasures;
    private int amountCorrectMeasures;
    private int amountAboveMeasures;
    private float averageMeasure;
    private int correctMeasuresPercent;
    private int totalMaterial;


    static DosingDevice create(DosingDevicePlcData plcData){
        return new DosingDevice(
                plcData.getRecordNumber(),
                plcData.getLastMeasure(),
                plcData.getAmountBelowMeasures(),
                plcData.getAmountCorrectMeasures(),
                plcData.getAmountAboveMeasures(),
                plcData.getAverageMeasure(),
                plcData.getCorrectMeasuresPercent(),
                plcData.getTotalMaterial()
        );
    }

    @Override
    public String toString() {
        try {
            return JsonConverter.toJson(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
