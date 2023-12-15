package christmas.model;

import christmas.constant.ErrorMessage;
import java.util.Arrays;
import java.util.List;

public class Orders {

    private final List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = orders;
    }

    public static Orders from(String value) {
        validate(value);
        List<Order> result = Arrays.stream(value.split(","))
                .map(Order::from)
                .toList();
        return new Orders(result);
    }

    private static void validate(String value) {
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        if (value.startsWith(",") || value.endsWith(",")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        if (value.contains(",,")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public int countCategory(Category category) {
        return orders.stream()
                .filter(order -> order.isCategory(category))
                .mapToInt(Order::getQuantity)
                .sum();
    }
}
