package christmas.model;

import christmas.constant.Message;
import christmas.constant.Rule;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class OrderHistory {

    private static final String SEPARATOR = ",";
    private final List<Order> orderHistory;

    private OrderHistory(List<Order> orderHistory) {
        validate(orderHistory);
        this.orderHistory = orderHistory;
    }

    private void validate(List<Order> target) {
        validateTotalQuantity(target);
        validateOnlyBeverage(target);
        validateDuplicate(target);
    }

    private void validateTotalQuantity(List<Order> target) {
        if (target.stream().mapToInt(Order::getQuantity).sum() > Rule.MAX_ORDER_QUANTITY.getValue()) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getMessage());
        }
    }

    private void validateOnlyBeverage(List<Order> target) {
        if (target.stream().allMatch(order -> order.isCategory(Category.BEVERAGE))) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getMessage());
        }
    }

    private void validateDuplicate(List<Order> target) {
        if (target.stream().distinct().count() != target.size()) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getMessage());
        }
    }

    public static OrderHistory from(String values) {
        validate(values);
        List<Order> orderHistory = Arrays.stream(values.trim().split(SEPARATOR))
                .map(order -> Order.from(order.trim()))
                .toList();
        return new OrderHistory(orderHistory);
    }

    private static void validate(String values) {
        validateType(values);
        validateSeparatorPosition(values);
        validateDuplicateSeparator(values);
    }

    private static void validateType(String values) {
        if (values == null || values.trim().isEmpty()) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getMessage());
        }
    }

    private static void validateSeparatorPosition(String values) {
        if (values.startsWith(SEPARATOR) || values.endsWith(SEPARATOR)) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getMessage());
        }
    }

    private static void validateDuplicateSeparator(String values) {
        if (values.contains(SEPARATOR.repeat(2))) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getMessage());
        }
    }

    public Integer getTotalAmount() {
        return orderHistory.stream()
                .mapToInt(Order::calculatePrice)
                .sum();
    }

    public Integer countCategory(Category category) {
        return orderHistory.stream()
                .filter(order -> order.isCategory(category))
                .mapToInt(Order::getQuantity)
                .sum();
    }

    public void forEach(Consumer<? super Order> action) {
        orderHistory.forEach(action);
    }

    @Override
    public String toString() {
        return orderHistory.toString();
    }
}
