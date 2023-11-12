package christmas.service.discount;

import christmas.constant.Rule;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

//TODO: 서비스라는 이름이 맞을까?
public class DiscountService {

    //TODO: Map<String, Integer> 이런 식으로 아예 이름 담아둘까?
    private final Map<DiscountPolicy, Integer> discounts;

    private DiscountService(Map<DiscountPolicy, Integer> discounts) {
        this.discounts = discounts;
    }

    public static DiscountService of(List<DiscountPolicy> discountPolicies, VisitDate date, OrderHistory orderHistory) {
        Map<DiscountPolicy, Integer> discounts = new LinkedHashMap<>();
        if (orderHistory.getTotalAmount() < Rule.MIN_AMOUNT_CONDITION) {
            return new DiscountService(discounts);
        }
        for (DiscountPolicy discountPolicy : discountPolicies) {
            int discountValue = discountPolicy.discount(date, orderHistory);
            if (discountValue > 0) {
                discounts.put(discountPolicy, -discountValue);
            }
        }
        return new DiscountService(discounts);
    }

    public void forEach(BiConsumer<? super DiscountPolicy, ? super Integer> action) {
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
