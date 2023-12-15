package christmas.model;

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
            throw new IllegalArgumentException();
        }
        if (value.startsWith(",") || value.endsWith(",")) {
            throw new IllegalArgumentException();
        }
        if (value.contains(",,")) {
            throw new IllegalArgumentException();
        }
    }
}
