package christmas.model;

import christmas.constant.Message;
import java.util.regex.Pattern;

public class Quantity {

    public static final Pattern NUMERIC_PATTERN = Pattern.compile("^[0-9]+$");
    private final int quantity;

    private Quantity(int quantity) {
        this.quantity = quantity;
    }

    public static Quantity from(String value) {
        validate(value);
        return new Quantity(Integer.parseInt(value.trim()));
    }

    private static void validate(String value) {
        validateType(value);
        validateRange(value);
    }

    private static void validateType(String value) {
        if (value == null || !NUMERIC_PATTERN.matcher(value.trim()).matches()) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getMessage());
        }
    }

    private static void validateRange(String value) {
        try {
            if (Integer.parseInt(value.trim()) < 1) {
                throw new IllegalArgumentException(Message.INVALID_ORDER.getMessage());
            }
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getMessage());
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
