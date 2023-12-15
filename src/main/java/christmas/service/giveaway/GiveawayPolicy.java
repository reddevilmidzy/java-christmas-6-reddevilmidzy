package christmas.service.giveaway;

import christmas.model.Event;
import christmas.model.Menu;
import christmas.model.Orders;

public interface GiveawayPolicy extends Event {

    boolean hasGiveaway(Orders orders);

    Menu getMenu();

    int getPrice();
}
