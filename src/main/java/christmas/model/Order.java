package christmas.model;

import java.util.Objects;

public class Order {

    private final Menu menu;
    private final Quantity quantity;

    private Order(Menu menu, Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order from(String source) {
        validate(source);
        Menu menu = Menu.from(source.substring(0, source.indexOf('-')));
        Quantity quantity = Quantity.from(source.substring(source.indexOf('-') + 1));
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

    public String getMenuName() {
        return menu.getName();
    }

    public int getQuantity() {
        return quantity.getQuantity();
    }

    public int calculatePrice() {
        return menu.getPrice() * quantity.getQuantity();
    }

    public Boolean isCategory(Category category) {
        return menu.isCategory(category);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Order order)) {
            return false;
        }
        return Objects.equals(menu, order.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }

    @Override
    public String toString() {
        return "Order{" +
                "menu=" + menu +
                ", quantity=" + quantity +
                "}";
    }
}
