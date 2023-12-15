package christmas.model;

import java.util.Arrays;

public enum Badge {
    VIP("산타", 20_000),
    GOLD("트리", 10_000),
    SILVER("별", 5_000),
    NEW("없음", 0),
    ;

    private final String name;
    private final Integer minimumAmount;

    Badge(String name, Integer minimumAmount) {
        this.name = name;
        this.minimumAmount = minimumAmount;
    }

    public static Badge from(Integer amount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.minimumAmount <= amount)
                .findFirst()
                .orElse(Badge.NEW);
    }

    public String getName() {
        return name;
    }
}