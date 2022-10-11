package app.web.weightModule.valueObject;

import app.web.utils.JsonConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class WeightModuleLastData {
    private WeightModuleFirstData moduleInfo;
    private LastModuleInfo lastModuleInfo;

    class LastModuleInfo{
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

    @Override
    public String toString() {
        try {
            return JsonConverter.toJson(this);
        } catch (IOException e) {
            return "Cannot parse " + this.getClass().toString() + " to JSON";
        }
    }
}
