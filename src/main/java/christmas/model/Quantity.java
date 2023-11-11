package christmas.model;

import java.util.regex.Pattern;

public class Quantity {

    //TODO: visitDate 중복 해결하기
    public static final Pattern NUMERIC_PATTERN = Pattern.compile("^[0-9]+$");
    private final int quantity;

    private Quantity(int quantity) {
        this.quantity = quantity;
    }

    public static Quantity from(String value) {
        validate(value);
        return new Quantity(Integer.parseInt(value));
    }

    private static void validate(String value) {
        validateType(value);
        validateRange(value);
    }

    private static void validateType(String value) {
        if (value == null || !NUMERIC_PATTERN.matcher(value.trim()).matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateRange(String value) {
        try {
            if (Integer.parseInt(value.trim()) < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.valueOf(quantity);
    }
}
