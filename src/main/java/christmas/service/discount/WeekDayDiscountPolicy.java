package christmas.service.discount;

import christmas.model.Category;
import christmas.model.Order;
import christmas.model.VisitDate;

public class WeekDayDiscountPolicy implements DiscountPolicy {

    private static final String name = "평일 할인";

    @Override
    public int discount(VisitDate visitDate, Order order) {
        if (visitDate.isWeekend()) {
            return 0;
        }
        return order.countCategory(Category.DESSERT) * 2023;
    }

    @Override
    public String getName() {
        return name;
    }
}
