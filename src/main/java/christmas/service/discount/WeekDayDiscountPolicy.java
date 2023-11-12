package christmas.service.discount;

import christmas.constant.Rule;
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
        return orderHistory.countCategory(Category.DESSERT) * Rule.DAY_DISCOUNT_AMOUNT;
    }

    @Override
    public String getName() {
        return name;
    }
}
