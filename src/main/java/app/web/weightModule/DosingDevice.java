package app.web.weightModule;

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


    @Override
    public String toString() {
        try {
            return JsonConverter.toJson(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
