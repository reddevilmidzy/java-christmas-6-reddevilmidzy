package christmas.service.giveaway;

import christmas.model.Menu;
import christmas.model.Orders;

public interface GiveawayPolicy {

    boolean hasGiveaway(Orders orders);

    String getName();

    Menu getMenu();

    int getPrice();
}
