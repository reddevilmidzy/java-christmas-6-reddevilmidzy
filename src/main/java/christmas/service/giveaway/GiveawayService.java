package christmas.service.giveaway;

import christmas.model.Menu;
import christmas.model.OrderHistory;

import java.util.List;
import java.util.function.Consumer;

//TODO: 이것도 discountService 처럼 이름 고민해뵉
public class GiveawayService {

    private final List<GiveawayPolicy> giveaway;

    private GiveawayService(List<GiveawayPolicy> giveaway) {
        this.giveaway = giveaway;
    }

    public static GiveawayService of(List<GiveawayPolicy> giveawayPolicies, OrderHistory orderHistory) {
        List<GiveawayPolicy> giveaway = giveawayPolicies.stream()
                .filter(giveawayPolicy -> giveawayPolicy.hasGiveaway(orderHistory))
                .toList();
        return new GiveawayService(giveaway);
    }

    public Integer calculateGiveawayBenefit() {
        return giveaway.stream()
                .mapToInt(this::getPrice)
                .sum();
    }

    private int getPrice(GiveawayPolicy giveawayPolicy) {
        Menu menu = giveawayPolicy.getMenu();
        return menu.getPrice();
    }

    public boolean isEmpty() {
        return giveaway.isEmpty();
    }

    public void forEach(Consumer<? super GiveawayPolicy> action) {
        giveaway.forEach(action);
    }
}
