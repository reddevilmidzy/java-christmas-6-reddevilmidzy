package christmas.service.discount;

import christmas.model.Category;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;

public class WeekDayDiscountPolicy implements DiscountPolicy {

    private static final String name = "평일 할인";

    @Override
    public int discount(VisitDate visitDate, OrderHistory orderHistory) {
        if (visitDate.isWeekend()) {
            return 0;
        }
        return orderHistory.countCategory(Category.DESSERT) * 2023;
    }

    @Override
    public String getName() {
        return name;
    }
}
