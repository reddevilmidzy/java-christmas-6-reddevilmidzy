package christmas.service.giveaway;

import christmas.constant.Rule;
import christmas.model.Menu;
import christmas.model.OrderHistory;

public class MenuGiveawayPolicy implements GiveawayPolicy {

    private static final String name = "증정 이벤트";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMenuName() {
        return Menu.CHAMPAGNE.getName();
    }

    @Override
    public int getPrice() {
        return Menu.CHAMPAGNE.getPrice();
    }

    @Override
    public boolean hasGiveaway(OrderHistory orderHistory) {
        return orderHistory.getTotalAmount() >= Rule.MIN_GIVEAWAY_EVENT_AMOUNT.getValue();
    }
}
