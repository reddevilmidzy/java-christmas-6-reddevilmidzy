package christmas.constant;

public enum Rule {
    MIN_AMOUNT_CONDITION(10_000),
    MAX_ORDER_QUANTITY(20),
    DISCOUNT_MAX_AMOUNT(3_400),
    AMOUNT_INCREASED(100),
    DAY_DISCOUNT_AMOUNT(2_023),
    SPECIAL_DISCOUNT_AMOUNT(1_000),
    MIN_GIVEAWAY_EVENT_AMOUNT(120_000),
    GIVEAWAY_MENU_COUNT(1),
    ;

    private final int value;

    Rule(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
