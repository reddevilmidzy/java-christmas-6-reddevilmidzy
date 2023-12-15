package christmas.repository;

import christmas.service.discount.DiscountPolicy;
import christmas.service.giveaway.GiveawayPolicy;
import java.util.Collections;
import java.util.List;

public class EventRepository {

    private final List<DiscountPolicy> discountPolicies;
    private final List<GiveawayPolicy> giveawayPolicies;

    public EventRepository(List<DiscountPolicy> discountPolicies, List<GiveawayPolicy> giveawayPolicies) {
        this.discountPolicies = discountPolicies;
        this.giveawayPolicies = giveawayPolicies;
    }

    public List<DiscountPolicy> getDiscountPolicies() {
        return Collections.unmodifiableList(discountPolicies);
    }

    public List<GiveawayPolicy> getGiveawayPolicies() {
        return Collections.unmodifiableList(giveawayPolicies);
    }
}
