package christmas.model;

import christmas.converter.Converter;
import christmas.converter.StringToInteger;

public class Order {

    private final static Converter<String, Integer> converter = new StringToInteger();
    private final Menu menu;
    private final Integer quantity;

    private Order(Menu menu, Integer quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order from(String source) {
        validate(source);
        Menu menu = Menu.from(source.substring(0, source.indexOf('-')));
        Integer quantity = converter.convert(source.substring(source.indexOf('-') + 1));
        return new Order(menu, quantity);
    }

    private static void validate(String source) {
        long separatorCount = source.chars()
                .filter(ch -> ch == '-')
                .count();
        if (separatorCount != 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "menu=" + menu +
                ", quantity=" + quantity +
                "}";
    }
}
