package christmas.model;

import christmas.constant.Message;
import java.util.Arrays;

public enum Menu {
    BUTTON_MUSHROOM_SOUP("양송이수프", Category.APPETIZER, 6_000),
    TAPAS("타파스", Category.APPETIZER, 5_500),
    CAESAR_SALAD("시저샐러드", Category.APPETIZER, 8_000),
    T_BONE_STEAK("티본스테이크", Category.MAIN_DISH, 55_000),
    BARBECUE_RIBS("바비큐립", Category.MAIN_DISH, 54_000),
    SEAFOOD_PASTA("해산물파스타", Category.MAIN_DISH, 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", Category.MAIN_DISH, 25_000),
    CHOCOLATE_CAKE("초코케이크", Category.DESSERT, 15_000),
    ICE_CREAM("아이스크림", Category.DESSERT, 5_000),
    ZERO_COLA("제로콜라", Category.BEVERAGE, 3_000),
    RED_WINE("레드와인", Category.BEVERAGE, 60_000),
    CHAMPAGNE("샴페인", Category.BEVERAGE, 25_000),
    ;

    private final String name;
    private final Category category;
    private final int price;

    Menu(String name, Category category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public static Menu from(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(Message.INVALID_ORDER.getMessage()));
    }

    public boolean isCategory(Category category) {
        return this.category.equals(category);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
