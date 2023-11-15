package christmas.model;

import java.util.Map;
import java.util.function.BiConsumer;

public class Benefit {
    private final Map<String, Integer> benefit;

    public Benefit(Map<String, Integer> benefit) {
        this.benefit = benefit;
    }

    public void forEach(BiConsumer<? super String, ? super Integer> action) {
        benefit.forEach(action);
    }

    public boolean isEmpty() {
        return benefit.isEmpty();
    }

    public int getTotalBenefit() {
        return -benefit.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public String toString() {
        return benefit.toString();
    }
}
