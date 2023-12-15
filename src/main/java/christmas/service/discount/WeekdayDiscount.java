package christmas.service.discount;

import christmas.model.Category;
import christmas.model.Orders;
import christmas.model.VisitDate;

public class WeekdayDiscount implements DiscountPolicy {

    private static final String name = "평일 할인";

    @Override
    public int discount(VisitDate visitDate, Orders orders) {
        if (visitDate.isWeekend()) {
            return 0;
        }
        return orders.countCategory(Category.DESSERT) * 2023;
    }

    @Override
    public String getName() {
        return name;
    }
}
