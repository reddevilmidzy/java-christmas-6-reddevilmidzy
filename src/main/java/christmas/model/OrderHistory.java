package christmas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class OrderHistory {

    private final List<Order> orderHistory;

    private OrderHistory(List<Order> orderHistory) {
        validate(orderHistory);
        this.orderHistory = orderHistory;
    }

    private void validate(List<Order> target) {
        validateTotalQuantity(target);
    }

    private void validateTotalQuantity(List<Order> target) {
        if (target.stream().mapToInt(Order::getQuantity).sum() > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static OrderHistory from(List<String> values) {
        List<Order> orderHistory = new ArrayList<>();
        for (String value : values) {
            Order order = Order.from(value);
            //TODO: 검증로직 분리
            if (orderHistory.contains(order)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            orderHistory.add(order);
        }
        return new OrderHistory(orderHistory);
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
