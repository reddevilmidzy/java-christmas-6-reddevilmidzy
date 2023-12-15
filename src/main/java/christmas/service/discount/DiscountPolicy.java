package christmas.service.discount;

import christmas.model.Orders;
import christmas.model.VisitDate;

public interface DiscountPolicy {

    int discount(VisitDate visitDate, Orders orders);

    String getName();
}
