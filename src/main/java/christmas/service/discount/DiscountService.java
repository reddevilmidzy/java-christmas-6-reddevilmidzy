package christmas.service.discount;

import christmas.model.Order;
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

    public static DiscountService of(List<DiscountPolicy> discountPolicies, VisitDate date, Order order) {
        Map<DiscountPolicy, Integer> discounts = new LinkedHashMap<>();
        for (DiscountPolicy discountPolicy : discountPolicies) {
            int discountValue = discountPolicy.discount(date, order);
            if (discountValue > 0) {
                discounts.put(discountPolicy, -discountPolicy.discount(date, order));
            }
        }
        return new DiscountService(discounts);
    }

    public void forEach(BiConsumer<? super DiscountPolicy, ? super Integer> action) {
        discounts.forEach(action);
    }
}
