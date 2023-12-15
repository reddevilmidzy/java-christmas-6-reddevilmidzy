package christmas.model;

import christmas.constant.ErrorMessage;
import java.util.Arrays;
import java.util.List;

public class Order {

    public static final int MENU_INDEX = 0;
    public static final int QUANTITY_INDEX = 1;

    private final Menu menu;
    private final int quantity;

    private Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order from(String value) {
        validate(value);
        List<String> separateValue = Arrays.stream(value.split("-")).toList();
        Menu menu = Menu.valueOfName(separateValue.get(MENU_INDEX));
        int quantity = Integer.parseInt(separateValue.get(QUANTITY_INDEX));
        return new Order(menu, quantity);
    }

    private static void validate(String value) {
        validateSeparator(value);
        List<String> target = Arrays.stream(value.split("-")).toList();
        validateQuantity(target.get(QUANTITY_INDEX));
    }

    private static void validateQuantity(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private static void validateSeparator(String value) {
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        if (value.startsWith("-") || value.endsWith("-")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        if (value.contains("--")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        if (!value.contains("-")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public boolean isCategory(Category category) {
        return menu.getCategory().equals(category);
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return menu.getName() + " " + quantity + "개";
    }
}
