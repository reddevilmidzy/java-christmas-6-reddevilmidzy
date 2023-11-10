package christmas.service.discount;

import christmas.model.Order;
import christmas.model.VisitDate;

public interface DiscountPolicy {
    int discount(VisitDate visitDate, Order order);

    String getName();
}
