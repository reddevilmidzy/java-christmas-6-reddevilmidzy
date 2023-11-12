package christmas.service.discount;

import christmas.constant.Rule;
import christmas.model.Category;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;

public class WeekendDiscountPolicy implements DiscountPolicy {

    private static final String name = "주말 할인";

    @Override
    public int discount(VisitDate visitDate, OrderHistory orderHistory) {
        if (visitDate.isWeekend()) {
            return orderHistory.countCategory(Category.MAIN_DISH) * Rule.DAY_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
