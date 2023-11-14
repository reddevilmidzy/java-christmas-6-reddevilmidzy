package christmas.service.giveaway;

import christmas.model.OrderHistory;

public interface GiveawayPolicy {
    boolean hasGiveaway(OrderHistory orderHistory);

    String getName();

    String getMenuName();

    int getPrice();
}
