package christmas.model;

import christmas.constant.Message;

import java.util.Objects;

public class Order {

    private static final String SEPARATOR = "-";
    private final Menu menu;
    private final Quantity quantity;

    private Order(Menu menu, Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order from(String source) {
        validate(source);
        Menu menu = Menu.from(getMenu(source));
        Quantity quantity = Quantity.from(getQuantity(source));
        return new Order(menu, quantity);
    }

    private static String getQuantity(String source) {
        return source.substring(source.indexOf(SEPARATOR) + 1);
    }

    private static String getMenu(String source) {
        return source.substring(0, source.indexOf(SEPARATOR));
    }

    private static void validate(String source) {
        if (!source.contains(SEPARATOR)) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getMessage());
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

    public boolean isCategory(Category category) {
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
