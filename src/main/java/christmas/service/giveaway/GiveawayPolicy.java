package christmas.service.giveaway;

import christmas.model.Menu;
import christmas.model.OrderHistory;

public interface GiveawayPolicy {
    boolean hasGiveaway(OrderHistory orderHistory);

    String getName();

    Menu getMenu();

    int getPrice();
}
