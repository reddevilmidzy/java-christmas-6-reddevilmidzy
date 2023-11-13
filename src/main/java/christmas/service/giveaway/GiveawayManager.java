package christmas.service.giveaway;

import christmas.model.OrderHistory;

import java.util.List;
import java.util.function.Consumer;

public class GiveawayManager {

    private final List<GiveawayPolicy> giveaway;

    private GiveawayManager(List<GiveawayPolicy> giveaway) {
        this.giveaway = giveaway;
    }

    public static GiveawayManager of(List<GiveawayPolicy> giveawayPolicies, OrderHistory orderHistory) {
        List<GiveawayPolicy> giveaway = giveawayPolicies.stream()
                .filter(giveawayPolicy -> giveawayPolicy.hasGiveaway(orderHistory))
                .toList();
        return new GiveawayManager(giveaway);
    }

    public Integer calculateGiveawayBenefit() {
        return giveaway.stream()
                .mapToInt(this::getPrice)
                .sum();
    }

    private int getPrice(GiveawayPolicy giveawayPolicy) {
        return giveawayPolicy.getPrice();
    }

    public boolean isEmpty() {
        return giveaway.isEmpty();
    }

    public void forEach(Consumer<? super GiveawayPolicy> action) {
        giveaway.forEach(action);
    }
}
