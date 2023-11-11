package christmas.service.giveaway;

import christmas.model.Menu;
import christmas.model.OrderHistory;

public class MenuGiveawayPolicy implements GiveawayPolicy {

    private static final String name = "증정 이벤트";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Menu getMenu() {
        return Menu.CHAMPAGNE;
    }

    @Override
    public boolean hasGiveaway(OrderHistory orderHistory) {
        return orderHistory.getTotalAmount() >= 120_000;
    }
}