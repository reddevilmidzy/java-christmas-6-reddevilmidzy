package christmas.service.discount;

import christmas.constant.Rule;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class DiscountManager {

    private final Map<String, Integer> discounts;

    private DiscountManager(Map<String, Integer> discounts) {
        this.discounts = discounts;
    }

    public static DiscountManager of(List<DiscountPolicy> discountPolicies, VisitDate date, OrderHistory orderHistory) {
        Map<String, Integer> discounts = new LinkedHashMap<>();
        if (orderHistory.getTotalAmount() < Rule.MIN_AMOUNT_CONDITION.getValue()) {
            return new DiscountManager(discounts);
        }
        for (DiscountPolicy discountPolicy : discountPolicies) {
            int discountValue = discountPolicy.discount(date, orderHistory);
            if (discountValue > 0) {
                discounts.put(discountPolicy.getName(), discountValue);
            }
        }
        return new DiscountManager(discounts);
    }

    public void forEach(BiConsumer<? super String, ? super Integer> action) {
        discounts.forEach(action);
    }

    public boolean isEmpty() {
        return discounts.isEmpty();
    }

    public Integer getBenefit() {
        return discounts.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
