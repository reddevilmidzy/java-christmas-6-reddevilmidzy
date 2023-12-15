package christmas.service.giveaway;

import christmas.model.Menu;
import christmas.model.Orders;

public class MenuGiveawayPolicy implements GiveawayPolicy {

    private static final String name = "증정 이벤트";
    private static final Menu giveMenu = Menu.CHAMPAGNE;

    @Override
    public boolean hasGiveaway(Orders orders) {
        return orders.calculateTotalAmount() >= 120_000;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Menu getMenu() {
        return giveMenu;
    }

    @Override
    public int getPrice() {
        return giveMenu.getPrice();
    }
}
