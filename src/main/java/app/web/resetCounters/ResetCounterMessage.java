package app.web.resetCounters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ResetCounterMessage {
    private LocalDateTime date;
    private List<Long> lineIds;
}
