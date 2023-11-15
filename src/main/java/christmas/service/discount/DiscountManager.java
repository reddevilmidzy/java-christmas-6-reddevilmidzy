package christmas.service.discount;

import christmas.constant.Rule;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class DiscountManager {

    private final Map<String, Integer> discounts;

    private DiscountManager(Map<String, Integer> discounts) {
        this.discounts = discounts;
    }

    public static DiscountManager of(List<DiscountPolicy> discountPolicies, VisitDate date, OrderHistory orderHistory) {
        if (orderHistory.getTotalAmount() < Rule.MIN_AMOUNT_CONDITION.getValue()) {
            return new DiscountManager(Map.of());
        }

        Map<String, Integer> discounts = discountPolicies.stream()
                .filter(discountPolicy -> discountPolicy.discount(date, orderHistory) > 0)
                .collect(Collectors.toMap(DiscountPolicy::getName,
                        discountPolicy -> discountPolicy.discount(date, orderHistory)));

        return new DiscountManager(discounts);
    }

    public void forEach(BiConsumer<? super String, ? super Integer> action) {
        discounts.forEach(action);
    }

    public int getBenefit() {
        return discounts.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
