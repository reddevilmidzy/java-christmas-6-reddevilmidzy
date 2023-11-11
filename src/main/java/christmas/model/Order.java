package christmas.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class Order {

    private final Map<Menu, Integer> orderHistory;

    private Order(Map<Menu, Integer> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public static Order from(List<String> values) {
        Map<Menu, Integer> result = new EnumMap<>(Menu.class);
        int menuCount = 0;
        for (String value : values) {
            //TODO: -가 두개 이상 포함되어 있는 경우 예외
            String[] split = value.split("-");
            Menu name = Menu.from(split[0]);
            int count = Integer.parseInt(split[1]);
            result.put(name, count);
            menuCount += count;
        }
        if (menuCount > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해주세요.");
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

    public void forEach(BiConsumer<? super Menu, ? super Integer> action) {
        orderHistory.forEach(action);
    }

    @Override
    public String toString() {
        return orderHistory.toString();
    }
}
