package christmas.model;

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
    CHAMPAGNE("샴페인", Category.BEVERAGE, 25_000);

    private final String name;
    private final Category category;
    private final Integer price;

    Menu(String name, Category category, Integer price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public static Menu from(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    public Boolean isCategory(Menu menu, Category category) {
        return category.equals(menu.category);
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
