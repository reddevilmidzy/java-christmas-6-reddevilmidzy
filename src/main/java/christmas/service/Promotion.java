package christmas.service;

import christmas.model.Event;
import christmas.model.Menu;
import christmas.model.Orders;
import christmas.model.VisitDate;
import christmas.repository.EventRepository;
import christmas.service.discount.DiscountPolicy;
import christmas.service.giveaway.GiveawayPolicy;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Promotion {
    private final Map<DiscountPolicy, Integer> discountPolicies;
    private final Map<GiveawayPolicy, Menu> giveawayPolicies;

    public Promotion(Map<DiscountPolicy, Integer> discountPolicies, Map<GiveawayPolicy, Menu> giveawayPolicies) {
        this.discountPolicies = discountPolicies;
        this.giveawayPolicies = giveawayPolicies;
    }

    public static Promotion of(EventRepository repository, VisitDate visitDate, Orders orders) {
        Map<DiscountPolicy, Integer> discount = applyDiscount(repository.getDiscountPolicies(),
                visitDate, orders);
        Map<GiveawayPolicy, Menu> giveaway = applyGiveaway(repository.getGiveawayPolicies(), orders);
        return new Promotion(discount, giveaway);
    }

    private static Map<DiscountPolicy, Integer> applyDiscount(List<DiscountPolicy> repository, VisitDate visitDate,
                                                              Orders orders) {
        Map<DiscountPolicy, Integer> result = new HashMap<>();
        for (DiscountPolicy discountPolicy : repository) {
            int discount = discountPolicy.discount(visitDate, orders);
            if (discount > 0) {
                result.put(discountPolicy, discount);
            }
        }
        return result;
    }

    private static Map<GiveawayPolicy, Menu> applyGiveaway(List<GiveawayPolicy> repository, Orders orders) {
        Map<GiveawayPolicy, Menu> result = new HashMap<>();
        for (GiveawayPolicy giveawayPolicy : repository) {
            if (giveawayPolicy.hasGiveaway(orders)) {
                result.put(giveawayPolicy, giveawayPolicy.getMenu());
            }
        }
        return result;
    }

    public List<String> getGiveawayMenus() {
        return giveawayPolicies.values().stream()
                .map(Menu::getName)
                .toList();
    }

    public Map<Event, Integer> calculateBenefit() {
        Map<Event, Integer> result = new LinkedHashMap<>();
        for (DiscountPolicy discountPolicy : discountPolicies.keySet()) {
            result.put(discountPolicy, discountPolicies.get(discountPolicy));
        }
        for (GiveawayPolicy giveawayPolicy : giveawayPolicies.keySet()) {
            result.put(giveawayPolicy, giveawayPolicies.get(giveawayPolicy).getPrice());
        }
        return result;
    }
}
