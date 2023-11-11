package christmas.service.discount;

import christmas.model.OrderHistory;
import christmas.model.VisitDate;

public interface DiscountPolicy {
    int discount(VisitDate visitDate, OrderHistory orderHistory);

    String getName();
}
