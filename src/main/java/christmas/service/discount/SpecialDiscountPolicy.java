package christmas.service.discount;

import christmas.constant.Rule;
import christmas.model.OrderHistory;
import christmas.model.VisitDate;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final String name = "특별 할인";

    @Override
    public int discount(VisitDate visitDate, OrderHistory orderHistory) {
        if (visitDate.isHoliday()) {
            return Rule.SPECIAL_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
