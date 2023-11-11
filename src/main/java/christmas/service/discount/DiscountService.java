package christmas.service.discount;

import christmas.model.OrderHistory;
import christmas.model.VisitDate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

//TODO: 서비스라는 이름이 맞을까?
public class DiscountService {

    private final Map<DiscountPolicy, Integer> discounts;

    private DiscountService(Map<DiscountPolicy, Integer> discounts) {
        this.discounts = discounts;
    }

    public static DiscountService of(List<DiscountPolicy> discountPolicies, VisitDate date, OrderHistory orderHistory) {
        Map<DiscountPolicy, Integer> discounts = new LinkedHashMap<>();
        if (orderHistory.getTotalAmount() < 10_000) {
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
        if (discounts.isEmpty()) {
            System.out.println("없음");
            return;
        }
        discounts.forEach(action);
    }

    public Integer getBenefit() {
        return discounts.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
