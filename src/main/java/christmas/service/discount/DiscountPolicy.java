package christmas.service.discount;

import christmas.model.Event;
import christmas.model.Orders;
import christmas.model.VisitDate;

public interface DiscountPolicy extends Event {

    int discount(VisitDate visitDate, Orders orders);

}
