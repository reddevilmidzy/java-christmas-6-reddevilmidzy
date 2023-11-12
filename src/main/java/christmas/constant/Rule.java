package christmas.constant;

public class Rule {

    public static final int MIN_AMOUNT_CONDITION = 10_000;
    public static final int MAX_ORDER_COUNT = 20;
    public static final int DISCOUNT_STARTING_AMOUNT = 1_000;
    public static final int AMOUNT_INCREASED = 100;
    public static final int DAY_DISCOUNT_AMOUNT = 2_023;
    public static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;
    public static final int MIN_GIVEAWAY_EVENT_AMOUNT = 120_000;
    public static final int GIVEAWAY_MENU_COUNT = 1;


    private Rule() {
        throw new IllegalArgumentException();
    }
}
