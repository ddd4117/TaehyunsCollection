package io.github.thcollection;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class CoronaData {
    long today = -1L;
    long yesterday = -1L;
    long increase = -1L;

//    @Builder
    public CoronaData(long today, long yesterday, long increase) {
        this.today = today;
        this.yesterday = yesterday;
        this.increase = increase;
    }

    @Builder
    public CoronaData(String today, String yesterday, String increase) {
        this.today = Long.parseLong(today.replace(",", "").trim());
        this.yesterday = Long.parseLong(yesterday.replace(",", "").trim());
        this.increase = Long.parseLong(increase.replace(",", "").trim());
    }
}
