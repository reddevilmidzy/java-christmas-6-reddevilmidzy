package christmas.service.discount;

import christmas.model.Category;
import christmas.model.Order;
import christmas.model.VisitDate;

public class WeekendDiscountPolicy implements DiscountPolicy {

    private static final String name = "주말 할인";

    @Override
    public int discount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekend()) {
            return order.countCategory(Category.MAIN_DISH) * 2023;
        }
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
