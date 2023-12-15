package christmas.service.discount;

import christmas.model.Orders;
import christmas.model.VisitDate;

public class SpecialDiscount implements DiscountPolicy {

    private static final String name = "특별 할인";

    @Override
    public int discount(VisitDate visitDate, Orders orders) {
        if (visitDate.isHoliday()) {
            return 1000;
        }
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
