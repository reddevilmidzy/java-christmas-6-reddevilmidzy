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

    //TODO: 이부분 그지 같아요 수정 필요
    public Integer getEventBenefit() {
        int result = 0;
        for (DiscountPolicy discountPolicy : discounts.keySet()) {
            if (Event.isEvent(discountPolicy.getClass())) {
                result += discounts.get(discountPolicy);
            }
        }
        return result;
    }
}
