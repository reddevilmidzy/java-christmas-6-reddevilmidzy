package christmas.service.discount;

import christmas.model.Category;
import christmas.model.Orders;
import christmas.model.VisitDate;

public class WeekendDiscount implements DiscountPolicy {

    private static final String name = "주말 할인";

    @Override
    public int discount(VisitDate visitDate, Orders orders) {
        if (visitDate.isWeekend()) {
            return orders.countCategory(Category.MAIN_DISH) * 2023;
        }
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
