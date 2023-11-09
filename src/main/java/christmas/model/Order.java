package christmas.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Order {

    private final Map<Menu, Integer> orderHistory;

    private Order(Map<Menu, Integer> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public static Order from(List<String> values) {
        Map<Menu, Integer> result = new EnumMap<>(Menu.class);
        for (String value : values) {
            //TODO: -가 두개 이상 포함되어 있는 경우 예외
            String[] split = value.split("-");
            Menu name = Menu.from(split[0]);
            Integer count = Integer.valueOf(split[1]);
            result.put(name, count);
        }
        return new Order(result);
    }

    public Integer getTotalAmount() {
        return orderHistory.keySet()
                .stream()
                .mapToInt(this::calculatePrice)
                .sum();
    }

    public Integer countCategory(Category category) {
        return orderHistory.keySet()
                .stream()
                .filter(menu -> menu.isCategory(menu, category))
                .mapToInt(orderHistory::get)
                .sum();
    }

    private Integer calculatePrice(Menu menu) {
        return menu.getPrice() * orderHistory.get(menu);
    }

    @Override
    public String toString() {
        return orderHistory.toString();
    }
}
