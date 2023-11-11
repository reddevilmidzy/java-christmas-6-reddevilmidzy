package christmas.service.giveaway;

import christmas.model.Menu;
import christmas.model.OrderHistory;

public interface GiveawayPolicy {
    boolean hasGiveaway(OrderHistory orderHistory);

    Menu getMenu();

    String getName();
}
